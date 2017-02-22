package au.com.codeka.carrot.expr;

/**
 * An enumeration of the different types of {@link Token}s we can pull off the statement parser.
 */
public enum TokenType {
  /** An unknown token, or the end of the stream. */
  UNKNOWN(false),

  /** A string literal "like this". */
  STRING_LITERAL(true),

  /** A number literal, like 12 or 12.34. */
  NUMBER_LITERAL(true),

  /** A Java-style identifier like foo or bar. */
  IDENTIFIER(true),

  /** Left-parenthesis: ( */
  LPAREN(false),

  /** Right-parenthesis: ( */
  RPAREN(false),

  /** Left-square-bracket: [ */
  LSQUARE(false),

  /** Right-square-bracket: ] */
  RSQUARE(false),

  COMMA(false),
  DOT(false),
  EQUALITY(false),
  INEQUALITY(false),
  LESS_THAN(false),
  LESS_THAN_OR_EQUAL(false),
  GREATER_THAN(false),
  GREATER_THAN_OR_EQUAL(false),
  PLUS(false),
  MINUS(false),
  MULTIPLY(false),
  DIVIDE(false);

  private final boolean hasValue;

  TokenType(boolean hasValue) {
    this.hasValue = hasValue;
  }

  public boolean hasValue() {
    return hasValue;
  }
}
