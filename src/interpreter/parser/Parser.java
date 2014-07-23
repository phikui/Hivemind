package interpreter.parser;

import interpreter.exceptions.SyntaxException;
import interpreter.rules.Expression;
import interpreter.rules.Number;
import interpreter.rules.Print;
import interpreter.rules.operators.BinaryOperator;
import interpreter.rules.operators.UnaryOperator;
import interpreter.rules.structure.IfExpression;
import interpreter.rules.structure.Loop;
import interpreter.rules.structure.Sequence;
import interpreter.rules.structure.WhileLoop;
import interpreter.rules.variables.Assignment;
import interpreter.rules.variables.DeleteVariable;
import interpreter.rules.variables.OneTimeAssignment;
import interpreter.rules.variables.ValueCheck;
import interpreter.rules.variables.Variable;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Parser {

	private static final boolean WHILE_ENABLED = true;
	static Stack<String> parseStack = new Stack<String>();
	static Stack<Expression> stack = new Stack<Expression>();
	static Queue<Expression> queue = new LinkedList<Expression>();

	protected static String formatter(String to_format) {
		String result;
		result = to_format;

		// c-style comment detecting
		result = result.replaceAll("//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/", "$1 ");

		// make sure operands have whitespace between them for easier parsing
		// a+b; becomes a + b ;
		result = result.replaceAll(";", " ; ");
		result = result.replaceAll("\\.", " .");
		result = result.replaceAll("\\+", " + ");
		result = result.replaceAll("-", " - ");
		result = result.replaceAll("\\*", " * ");
		result = result.replaceAll("/", " / ");
		result = result.replaceAll("%", " % ");
		result = result.replaceAll("<", " < ");
		result = result.replaceAll(">", " > ");
		result = result.replaceAll("&", " & ");
		result = result.replaceAll("\\|", " | ");
		result = result.replaceAll("=", " = ");
		result = result.replaceAll("!", " ! ");
		result = result.replaceAll("\\?", " ? ");
		result = result.replaceAll("�", " � ");
		result = result.replaceAll("\\(", " ( ");
		result = result.replaceAll("\\)", " ) ");

		result = result.replaceAll("\\s+", " "); // remove double whitespace

		// put multi-character operands back together
		result = result.replace("= =", "==");
		result = result.replace("+ +", "++");
		result = result.replace("+ =", "+=");
		result = result.replace("- =", "-=");
		result = result.replace("* =", "*=");
		result = result.replace("/ =", "/=");
		result = result.replace("< =", "<=");
		result = result.replace("> =", ">=");
		result = result.replace("# =", "#=");
		// System.out.println(result);
		return result;
	}

	protected static Executable multiLineParse(String to_parse) throws SyntaxException {
		to_parse = formatter(to_parse);

		return new Executable(parseLine(to_parse, null));

	}

	/*
	 * Input: a line that has to be parsed
	 * 
	 * It will parse from left to right one item at a time with the rest to be
	 * parsed recursively
	 */

	protected static Expression parseLine(String line, Expression previous) throws SyntaxException {
		if (line.length() > 0) {

			String[] tokens = line.split(" ");
			if (tokens.length == 0) {
				return null;
			}
			boolean has_rest = tokens.length > 1;
			String token = tokens[0];
			// System.out.println("Evaluating: " + token);
			String restOfLine = "";
			if (has_rest) {
				StringBuffer out = new StringBuffer();
				for (int i = 1; i < tokens.length; i++) {
					out.append(tokens[i]);
					out.append(' ');
				}
				restOfLine = out.toString();
			}

			// Operands: require a left side stored in previous, the right side
			// is called recursively

			// binary operators
			if (token.equals("!=") || token.equals("==") || token.equals("+") || token.equals("-") || token.equals("*")
					|| token.equals("/") || token.equals("<") || token.equals(">") || token.equals("&")
					|| token.equals("|") || token.equals("?") || token.equals("%") || token.equals("<=")
					|| token.equals(">=")) {
				if (previous == null) {
					throw new SyntaxException("Expected left operand for " + token);
				} else if (!has_rest) {
					throw new SyntaxException("Expected right operand for " + token);
				}

				return new BinaryOperator(token, previous, parseLine(restOfLine, null));

			} else if (token.equals("")) {
				if (has_rest) {
					return parseLine(restOfLine, null);
				} else {
					return null;
				}
				// AbbreviationOperators
			} else if (token.equals("+=") || token.equals("-=") || token.equals("*=") || token.equals("/=")
					|| token.equals("++")) {
				if (previous instanceof Variable) {
					Variable var = (Variable) previous;

					if (token.equals("++")) {
						return new Assignment(var.getName(), new BinaryOperator("+", previous, new Sequence(new Number(
								1), parseLine(restOfLine, null))));
					}
					if (has_rest) {
						return new Assignment(var.getName(), new BinaryOperator(token.charAt(0) + "", previous,
								parseLine(restOfLine, null)));

					} else {
						throw new SyntaxException("Nothing to assign for " + token);
					}
				} else {
					throw new SyntaxException("No variable for " + token);

				}
				// special variable operators
			} else if (token.equals(".exists") || token.equals(".delete")) {
				if (previous == null) {
					throw new SyntaxException("Expected operand for " + token);
				}
				if (previous instanceof Variable) {
					Expression op = null;
					Variable var = (Variable) previous;

					if (token.equals(".exists")) {
						op = new ValueCheck(var);
					} else if (token.equals(".delete")) {
						op = new DeleteVariable(var);
					}

					if (has_rest) {

						return parseLine(restOfLine, op);
					} else {
						return op;
					}
				} else {
					throw new SyntaxException("Can't invoke " + token + " on " + previous.toString());
				}

				// unary operators
			} else if (token.equals("!") || token.equals("sqrt") || token.equals("abs") || token.equals("log")) {

				if (has_rest) {
					return new UnaryOperator(token, parseLine(restOfLine, null));
				} else {
					throw new SyntaxException("Expected operand for " + token);
				}

			} else if (token.equals("(")) {

				int level = 1; // starting level for the search of the closing
								// bracket
				int closing_index = -1; // the point where the brackets are
										// closed
				StringBuffer inside = new StringBuffer(); // the part inside the
															// brackets
				StringBuffer rest = new StringBuffer(); // the rest of the line
														// without the brackets
				if (!has_rest) {
					throw new SyntaxException("Expected )");
				}
				for (int i = 1; i < tokens.length; i++) {
					if (tokens[i].equals("(")) {
						level++;
					} else if (tokens[i].equals(")")) {
						level--;
						if (level == 0) {
							closing_index = i;
							break;
						}

					}
					inside.append(tokens[i]);
					inside.append(" ");
				}
				if (closing_index == -1) {
					throw new SyntaxException("Expected )");
				}

				for (int i = closing_index + 1; i < tokens.length; i++) {
					rest.append(tokens[i]);
					rest.append(' ');
				}

				Expression inside_expression = parseLine(inside.toString(), null);
				if (rest.toString().length() > 0) {
					return parseLine(rest.toString(), inside_expression);
				} else {
					return inside_expression;
				}

			} else if (token.equals("=") || token.equals("#=")) {
				if (previous instanceof Variable) {
					if (has_rest) {
						Variable var = (Variable) previous;
						if (token.equals("=")) {
							return new Assignment(var.getName(), parseLine(restOfLine, null));
						} else {
							return new OneTimeAssignment(var.getName(), parseLine(restOfLine, null));
						}
					} else {
						throw new SyntaxException("Nothing to assign");
					}
				} else {
					throw new SyntaxException("No variable for " + token);
				}

			} else if (token.equals(";")) {

				if (has_rest) {
					return new Sequence(previous, parseLine(restOfLine, null));
				} else {
					return new Sequence(previous, null);
				}

			} else if (token.equals("LOOP") || (token.equals("WHILE") && WHILE_ENABLED)) {
				if (!has_rest) {
					throw new SyntaxException("Expected statements after " + token);
				}

				int level; // starting level for the search of the closing
				// of the loop
				int closing_index_condition = -1; // the point where the
													// condition is closed
				int closing_index_loop = -1; // the point where the ENDFOR is

				StringBuffer condition = new StringBuffer();
				StringBuffer loop = new StringBuffer();
				StringBuffer rest = new StringBuffer();

				// searching for end of condition
				level = 0;
				for (int i = 1; i < tokens.length; i++) {
					if (tokens[i].equals("LOOP") || tokens[i].equals("WHILE")) {
						level++;
					} else if (tokens[i].equals("ENDLOOP")) {
						level--;
					}

					if (tokens[i].equals("DO") && level == 0) {
						closing_index_condition = i;
						break;
					}
					condition.append(tokens[i]);
					condition.append(" ");
				}

				if (closing_index_condition == -1) {
					throw new SyntaxException("Expected DO after " + token);
				}

				level = 1;
				for (int i = closing_index_condition + 1; i < tokens.length; i++) {
					if (tokens[i].equals("LOOP") || tokens[i].equals("WHILE")) {
						level++;
					} else if (tokens[i].equals("ENDLOOP")) {
						level--;
					}

					if (tokens[i].equals("ENDLOOP") && level == 0) {
						closing_index_loop = i;

						break;
					}
					loop.append(tokens[i]);
					loop.append(' ');
				}
				if (closing_index_loop == -1) {
					throw new SyntaxException("Expected ENDLOOP after " + token);
				}

				for (int i = closing_index_loop + 1; i < tokens.length; i++) {
					rest.append(tokens[i]);
					rest.append(' ');
				}

				Expression cond = parseLine(condition.toString(), null);
				Expression inner = parseLine(loop.toString(), null);
				Expression loopExpression;
				if (token.equals("LOOP")) {
					loopExpression = new Loop(cond, inner);
				} else {
					loopExpression = new WhileLoop(cond, inner);
				}
				if (rest.toString().length() > 0) {
					return new Sequence(loopExpression, parseLine(rest.toString(), null));
				} else {
					return loopExpression;
				}

			} else if (token.equals("IF")) {
				if (!has_rest) {
					throw new SyntaxException("Exprected statements after " + token);
				}

				int level = 1; // starting level for the search of the closing
				// of the if
				int closing_index_condition = -1; // the point where the
													// condition is closed
				int closing_index_true = -1; // the point where the else is
				int closing_index_false = -1; // the point where the endif is
				boolean has_else = false;
				StringBuffer condition = new StringBuffer();
				StringBuffer true_case = new StringBuffer();
				StringBuffer false_case = new StringBuffer();
				StringBuffer rest = new StringBuffer();

				// searching for end of condition
				level = 0;
				for (int i = 1; i < tokens.length; i++) {
					if (tokens[i].equals("IF")) {
						level++;
					} else if (tokens[i].equals("ENDIF")) {
						level--;
					}

					if (tokens[i].equals("THEN") && level == 0) {
						closing_index_condition = i;
						break;
					}
					condition.append(tokens[i]);
					condition.append(" ");
				}

				if (closing_index_condition == -1) {
					throw new SyntaxException("Expected THEN after " + token);
				}

				level = 0;
				has_else = false;
				for (int i = closing_index_condition + 1; i < tokens.length; i++) {
					if (tokens[i].equals("IF")) {
						level++;
					} else if (tokens[i].equals("ENDIF")) {
						level--;
					}

					if ((tokens[i].equals("ELSE") || tokens[i].equals("ENDIF")) && level <= 0) {
						closing_index_true = i;

						if (tokens[i].equals("ELSE")) {
							has_else = true;
						}
						break;
					}
					true_case.append(tokens[i]);
					true_case.append(' ');
				}

				if (has_else == false) {
					closing_index_false = closing_index_true;
					false_case.append("");
				} else {
					level = 1;
					for (int i = closing_index_true + 1; i < tokens.length; i++) {
						if (tokens[i].equals("IF")) {
							level++;
						} else if (tokens[i].equals("ENDIF")) {
							level--;
							if (level == 0) {
								closing_index_false = i;
								break;
							}
						}
						false_case.append(tokens[i]);
						false_case.append(' ');
					}
				}

				for (int i = closing_index_false + 1; i < tokens.length; i++) {
					rest.append(tokens[i]);
					rest.append(' ');
				}

				Expression conditionExpression = parseLine(condition.toString(), null);
				Expression trueExpression = parseLine(true_case.toString(), null);
				Expression falseExpression = parseLine(false_case.toString(), null);
				Expression ifStatement = new IfExpression(conditionExpression, trueExpression, falseExpression);

				if (rest.toString().length() > 0) {
					return new Sequence(ifStatement, parseLine(rest.toString(), null));
				} else {
					return ifStatement;
				}

			} else if (token.equals("print")) {
				if (!has_rest) {
					throw new SyntaxException("Expected message for " + token);
				}
				return new Print(parseLine(restOfLine, null));

			} else if (isNumeric(token)) {
				Expression numeric;
				numeric = new Number(Double.parseDouble(token));

				if (!has_rest) {
					return numeric;
				} else {
					return parseLine(restOfLine, numeric);
				}
				// token is variable name
			} else {
				// System.out.println("Token is variable");
				Expression variable = new Variable(token);
				if (!has_rest) {
					return variable;
				} else {
					return parseLine(restOfLine, variable);
				}
			}
		} else {
			return null;
		}

	}

	private static boolean isNumeric(String str) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);

		return str.length() == pos.getIndex();
	}
}
