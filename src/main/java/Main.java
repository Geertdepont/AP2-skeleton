import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	
	// Method to read 1 character.
	char nextChar (Scanner in, boolean skipWhiteSpace) {
		skipWhiteSpace(in,skipWhiteSpace);
		return in.next().charAt(0);
	}
	// Method to check if the next character to be read when
	// calling nextChar() is equal to the provided character.
	boolean nextCharIs(Scanner in, char c, boolean skipWhiteSpace) {
		skipWhiteSpace(in,skipWhiteSpace);
		return in.hasNext(Pattern.quote(c+""));
	}
	
	// Method to check if the next character to be read when
	// calling nextChar() is a digit.
	boolean nextCharIsDigit (Scanner in, boolean skipWhiteSpace) {
		skipWhiteSpace(in,skipWhiteSpace);
		return in.hasNext("[0-9]");
	}
	
	// Method to check if the next character to be read when
	// calling nextChar() is a letter.
	boolean nextCharIsLetter (Scanner in, boolean skipWhiteSpace) {
		skipWhiteSpace(in,skipWhiteSpace);
		return in.hasNext("[a-zA-Z]");
	}
	
	void skipWhiteSpace(Scanner in, boolean skipWhiteSpace){
		in.useDelimiter("");
		if(skipWhiteSpace){
			while(in.hasNext("\\s+")){
				in.next().charAt(0);
			}
		}
	}

    void eoln(Scanner input) throws APException{
    	if(input.hasNext()){
    		throw new APException("expected end-of-line");
    	}
    }
    
    void eof(Scanner input) throws APException{
    	if(input.hasNext()){
    		throw new APException("expected end-of-file");
    	}
    }
    
    void character (Scanner input, char c) throws APException{
    	if (!nextCharIs(input, c, true)){
    	throw new APException("The next character is not "+c);
        }
        nextChar(input, true);
    }
    
    boolean nextIsAdditiveOperator(Scanner input){
    	return nextCharIs(input, '+', true) || nextCharIs(input, '|', true) || nextCharIs(input, '-', true); 
    }
    
    boolean nextIsMultiplicativeOperator(Scanner input){
    	return nextCharIs(input, '*', true);
    }
    
    void program(Scanner input) throws APException{
    	while(input.hasNextLine()){ //Reads the whole file? 
    		statement(input);
    	}
    	eof(input);//do we need this? (the EBNF implies we need it, but it will never throw the exception)
    }
    
    void statement(Scanner input) throws APException{
    	String statement=input.nextLine();
    	Scanner statementScanner=new Scanner(statement);

    	if(nextCharIsLetter(statementScanner, true)){
    		assignment(statementScanner);
    	}else if(nextCharIs(statementScanner, '?', true)){
    		printStatement(statementScanner);
    	}else if(nextCharIs(statementScanner, '/', true)){
    		comment(statementScanner);
    	}else{
    		throw new APException("Misformed statement. A statement has to begin with a '?', '/' or an identifier");
    	}
    }
    
    void assignment(Scanner input) throws APException{
    	Identifier identifier=identifier(input);
    	character(input, '=');
       	expression(input);
    	eoln(input);
    }
    
    void printStatement(Scanner input) throws APException{
    	character(input, '?');
    	expression(input);
    	eoln(input);
    }
    
    void comment(Scanner input) throws APException{
    	character(input, '/');
    	if(input.hasNextLine()){//to allow an empty line following the '/' ('/' followed IMMEDIATELY by <eol>)
    		input.nextLine();
    	}
    	eoln(input);
    }
    
    Identifier identifier(Scanner input) throws APException{
    	StringBuffer identifier = new StringBuffer();
    	
    	if(nextCharIsLetter(input, true)){
    		identifier.append(letter(input));
    	}else{
    		throw new APException("identifier has to start with a letter");
    	}
    	while(nextCharIsLetter(input, false) || nextCharIsDigit(input, false)){
    		if(nextCharIsLetter(input, false)){
    			identifier.append(letter(input));
    		}else{
    			identifier.append(number(input));
    		}
    	}
    	return new Identifier(identifier.toString());
    }

    void expression(Scanner input) throws APException{
    	term(input);
    	
    	while(nextIsAdditiveOperator(input)){
    		additiveOperator(input);
    		term(input);
    	}
    	
    }
    
    void term(Scanner input) throws APException{
    	factor(input);
    	while(nextIsMultiplicativeOperator(input)){
    		multiplicativeOperator(input);
    		factor(input);
    	}
    }
    
    void factor(Scanner input) throws APException{
    	if(nextCharIsLetter(input, true)){
			identifier(input);
		}else if(nextCharIs(input, '(', true)){
			complexFactor(input);
		}else if(nextCharIs(input, '{', true)){
			set(input);
		}else{
			throw new APException("Misformed factor. A factor has to begin with an identifier, '(' or '{'");
		}
    }
    
    void complexFactor(Scanner input) throws APException{
    	character(input, '(');
    	expression(input);
    	character(input, ')');
    }
    
    void set(Scanner input) throws APException{
    	character(input, '{');
    	rowNaturalNumbers(input);
    	character(input, '}');
    }
    
    void rowNaturalNumbers(Scanner input) throws APException{
    	if(nextCharIsDigit(input, true)){
    		naturalNumber(input);
    		while(nextCharIs(input, ',', true)){
    			character(input, ',');
    			naturalNumber(input);
    		}
    	}
    }
    
    char additiveOperator(Scanner input) throws APException{
    	if(!nextCharIs(input, '+', true) && !nextCharIs(input, '|', true) && !nextCharIs(input, '-', true)){
    		throw new APException("is not additive operator sign");
    	}
    	return nextChar(input, true);
    }
    
    char multiplicativeOperator(Scanner input) throws APException{
    	if(!nextCharIs(input, '*', true)){
    		throw new APException("is not multiplication sign");
    	}
    	return nextChar(input, true);
    }
    
    int naturalNumber(Scanner input) throws APException{
    	return nextCharIs(input, '0', true) ? zero(input):positiveNumber(input);
    }
    
    int positiveNumber(Scanner input) throws APException{
    	StringBuffer result=new StringBuffer();
    	result.append(notZero(input));
    	while(nextCharIsDigit(input, false)){
    		result.append(number(input));
    	}
    	return Integer.parseInt(result.toString());
    }
    
    int number(Scanner input) throws APException{
    	return nextCharIs(input, '0' ,false) ? zero(input) : notZero(input);
    }
    
    int zero(Scanner input) throws APException{
    	if(!nextCharIs(input, '0', false)){
    		throw new APException("is not zero");
    	}
    	return Character.getNumericValue(nextChar(input, false));
    }
    
	int notZero(Scanner input) throws APException{
		if(!input.hasNext("[1-9]")){
			throw new APException("is not value bewteen 1-9");
		}
		return Character.getNumericValue(nextChar(input, false));
	}
    
    char letter(Scanner input) throws APException{
    	if(!nextCharIsLetter(input, false)){
			throw new APException("is not a letter");
		}
    	return nextChar(input, false);
    }
    
    private void start() {
        // Create a scanner on System.in
        // While there is input, read line and parse it.
    	Scanner programScanner=new Scanner(System.in);
    	try{
    		program(programScanner);
    	}catch(APException e){
//    		throw new Error(e);
    		System.out.println(e+"\n");
    		start();
    	}
    }
    
    public static void main(String[] argv) {
        new Main().start();
    }
}
