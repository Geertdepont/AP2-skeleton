import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	
	HashMap<Identifier, Set<BigInteger>> calculatorHashMap;
	
	PrintStream out;
	InputStream in;
	
	Main(){
		out=System.out;
		in=System.in;
		calculatorHashMap=new HashMap<Identifier, Set<BigInteger>>();
	}
	
	char nextChar (Scanner in, boolean skipWhiteSpace){
		if(skipWhiteSpace){
			skipWhiteSpace(in);
		}
		return in.next().charAt(0);
	}

	boolean nextCharIs(Scanner in, char c, boolean skipWhiteSpace){
		if(skipWhiteSpace){
			skipWhiteSpace(in);
		}
		return in.hasNext(Pattern.quote(c+""));
	}
	
	boolean nextCharIsDigit (Scanner in, boolean skipWhiteSpace){
		if(skipWhiteSpace){
			skipWhiteSpace(in);
		}
		return in.hasNext("[0-9]");
	}
	
	boolean nextCharIsLetter (Scanner in, boolean skipWhiteSpace){
		if(skipWhiteSpace){
			skipWhiteSpace(in);
		}
		return in.hasNext("[a-zA-Z]");
	}
	
	void skipWhiteSpace(Scanner in){
		while(in.hasNext("\\s+")){
			in.next().charAt(0);
		}
	}

    void eoln(Scanner input) throws APException{
    	if(input.hasNext()){
    		throw new APException("Error: Expected end-of-line");
    	}
    }
    
    void eof(Scanner input) throws APException{
    	if(input.hasNext()){
    		throw new APException("Error: Expected end-of-file");
    	}
    }
    
    void character (Scanner input, char c) throws APException{
    	if (!nextCharIs(input, c, true)){
    	throw new APException("Error: The next character is not "+c);
        }
        nextChar(input, true);
    }
    
    void printSet(Set<BigInteger> input){
    	Set<BigInteger> temp=(Set<BigInteger>) input.copy();
    	while(!temp.isEmpty()){
    		BigInteger element=temp.getRandom();
    		temp.remove(element);
    		out.printf("%s ",element.toString());
    	}
    	out.printf("\n");
    }
    
    boolean nextIsAdditiveOperator(Scanner input){
    	return nextCharIs(input, '+', true) || nextCharIs(input, '|', true) || nextCharIs(input, '-', true); 
    }
    
    boolean nextIsMultiplicativeOperator(Scanner input){
    	return nextCharIs(input, '*', true);
    }
    
    char letter(Scanner input) throws APException{
    	if(!nextCharIsLetter(input, false)){
			throw new APException("Error: Expected a letter");
		}
    	return nextChar(input, false);
    }
    
    String notZero(Scanner input) throws APException{
		if(!input.hasNext("[1-9]")){
			throw new APException("Error: Expected a number (1-9)");
		}
		return "" + nextChar(input, false);
	}
    
    String zero(Scanner input) throws APException{
    	if(!nextCharIs(input, '0', false)){
    		throw new APException("Error: Expected a zero (0)");
    	}
    	return "" + nextChar(input, false);
    }
    
    String number(Scanner input) throws APException{
    	return nextCharIs(input, '0' ,false) ? zero(input) : notZero(input);
    }
    
    String positiveNumber(Scanner input) throws APException{
    	StringBuffer result=new StringBuffer();
    	result.append(notZero(input));
    	while(nextCharIsDigit(input, false)){
    		result.append(number(input));
    	}
    	return result.toString();
    }
    
    BigInteger naturalNumber(Scanner input) throws APException{
    	return nextCharIs(input, '0', true) ? new BigInteger(zero(input)):new BigInteger(positiveNumber(input));
    }
    
    char multiplicativeOperator(Scanner input) throws APException{
    	if(!nextCharIs(input, '*', true)){
    		throw new APException("Error: Expected a multiplicative operator sign (*)");
    	}
    	return nextChar(input, true);
    }
    
    char additiveOperator(Scanner input) throws APException{
    	if(!nextCharIs(input, '+', true) && !nextCharIs(input, '|', true) && !nextCharIs(input, '-', true)){
    		throw new APException("Error: Expected additive operator sign (+, | or -)");
    	}
    	return nextChar(input, true);
    }
    
    Set<BigInteger> rowNaturalNumbers(Scanner input) throws APException{
    	Set<BigInteger> result=new Set<BigInteger>();
    	if(nextCharIsDigit(input, true)){
    		result.add(naturalNumber(input));
    		while(nextCharIs(input, ',', true)){
    			character(input, ',');
    			result.add(naturalNumber(input));
    		}
    	}
    	return result;
    }
    
    Set<BigInteger> set(Scanner input) throws APException{
    	character(input, '{');
    	Set<BigInteger> result=rowNaturalNumbers(input);
    	character(input, '}');
    	return result;
    }
    
    Set<BigInteger> complexFactor(Scanner input) throws APException{
    	character(input, '(');
    	Set<BigInteger> result=expression(input);
    	character(input, ')');
    	return result;
    }
    
    Set<BigInteger> factor(Scanner input) throws APException{
    	Set<BigInteger> result;
    	
    	if(nextCharIsLetter(input, true)){
    		Identifier key=identifier(input);
			if(!calculatorHashMap.containsKey(key)){
				throw new APException("Error: Undefined variable");
			}
			else{
				result=calculatorHashMap.get(key);
			}
		}else if(nextCharIs(input, '(', true)){
			result=complexFactor(input);
		}else if(nextCharIs(input, '{', true)){
			result=set(input);
		}else{
			throw new APException("Error: Misformed factor. A factor has to begin with an identifier, '(' or '{'");
		}
    	return result;
    }
    
    Set<BigInteger> term(Scanner input) throws APException{
    	Set<BigInteger> result=factor(input);
    	
    	while(nextIsMultiplicativeOperator(input)){
    		multiplicativeOperator(input);
    		Set<BigInteger>temp=factor(input);
    		result=(Set<BigInteger>) result.intersection(temp);
    	}
    	return result;
    }
    
    Set<BigInteger> expression(Scanner input) throws APException{
    	Set<BigInteger> result=term(input);
    	
    	while(nextIsAdditiveOperator(input)){
    		char operation=additiveOperator(input);
    		Set<BigInteger> temp=term(input);
    		if(operation=='+'){
    			result=(Set<BigInteger>) result.union(temp);
    		}
    		else if(operation=='|'){
    			result=(Set<BigInteger>) result.symmetricDifference(temp);
    		}
    		else if(operation=='-'){
    			result=(Set<BigInteger>) result.complement(temp);
    		}
    	}
    	return result;
    }
    
    Identifier identifier(Scanner input) throws APException{
    	StringBuffer identifier=new StringBuffer();
    	
    	if(nextCharIsLetter(input, true)){
    		identifier.append(letter(input));
    	}else{
    		throw new APException("Error: Identifier has to start with a letter");
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
    
    void comment(Scanner input) throws APException{
    	character(input, '/');
    	if(input.hasNextLine()){
    		input.nextLine();
    	}
    	eoln(input);
    }
    
    void printStatement(Scanner input) throws APException{
    	character(input, '?');
    	Set<BigInteger> set=expression(input);
    	eoln(input);
    	printSet(set);
    }
    
    void assignment(Scanner input) throws APException{
    	Identifier key=identifier(input);
    	try{
    		character(input, '=');
    	}catch (APException e){
    		throw new APException("Error: Expected equals sign (=)");
    	}
    	Set<BigInteger> value;
		value=expression(input);
		eoln(input);
		calculatorHashMap.put(key, value);
    }
    
    void statement(Scanner input) throws APException{
    	String statement=input.nextLine();
    	Scanner statementScanner=new Scanner(statement);
    	statementScanner.useDelimiter("");
    	
    	if(nextCharIsLetter(statementScanner, true)){
    		assignment(statementScanner);
    	}else if(nextCharIs(statementScanner, '?', true)){
    		printStatement(statementScanner);
    	}else if(nextCharIs(statementScanner, '/', true)){
    		comment(statementScanner);
    	}else{
    		throw new APException("Error: Misformed statement. A statement has to begin with a '?', '/' or an identifier");
    	}
    }
    
    void program(Scanner input) throws APException{
    	while(input.hasNextLine()){
    		statement(input);
    	}
    	eof(input);
    }
    
    private void start(){
    	Scanner programScanner=new Scanner(in);
    	
    	while(programScanner.hasNextLine()){
    		try{
        		program(programScanner);
        	}catch(APException e){
        		out.println(e);
        	}
    	}
    }
    
    public static void main(String[] argv){
        new Main().start();
    }
}
