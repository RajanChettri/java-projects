package ca.ubc.ece.eece210.mp3.ast;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a hand-written recursive descent parser that will build an AST for
 * our simple query language.
 * 
 * To use this class, create a new instance of SimpleParser and call the
 * getRoot() method.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public class QueryParser {
    private static final Token END_TOKEN = new Token( TokenType.END, "END" );
    final List<Token> tokenStream;
    int currentPosition;
    ASTNode root;

    // Creates a map to be used to map a token type to the associated class that
    // extends ASTNode
    static Map<TokenType, Class<? extends ASTNode>> map = new HashMap<TokenType, Class<? extends ASTNode>>();

    // initialize the map between token type and AST class type
    static {
        map.put( TokenType.AND, AndNode.class );
        map.put( TokenType.OR, OrNode.class );
        map.put( TokenType.MATCHES, MatchesNode.class );
        map.put( TokenType.IN, InNode.class );
        map.put( TokenType.BY, ByNode.class );
    }

    /**
     * Constructor for the QueryParser that takes a List of tokens as argument.
     */
    public QueryParser(List<Token> _tokenStream) {
        tokenStream = _tokenStream;
        currentPosition = 0;
    }

    /**
     * Returns the root node for the query.
     * 
     * @return the root node for the query.
     */
    public ASTNode getRoot() {
        ASTNode ast = orExpr(); // Or is the root node, not and
        return ast;
    }


    /**
     * Process an OR expression and return a node that represents the
     * root of the tree for the OR expression.
     * 
     * This method determines if the next unprocessed portion of the query
     * is an OR expression or not. If it is then it creates a tree that
     * represents
     * the OR expression. This tree typically become a subtree of the AST for
     * the
     * complete query.
     * 
     * @return the root of the tree that represents an OR expression.
     */
    public ASTNode orExpr() {
        /* TODO: implement me */// Complete this to build AST.
        ASTNode current;

        ASTNode leftTree = andExpr();
        current = leftTree;

        Token nextToken;

        do {
            nextToken = peek();

            if ( nextToken.getType() == TokenType.OR ) {
                consume();
                ASTNode head = new OrNode( Token.getTokenInstance( nextToken
                        .getPayload() ) );
                head.addChild( current );
                ASTNode rightTree = andExpr();
                head.addChild( rightTree );
                current = head;

            } else {
                return current;
            }

        } while ( nextToken.getType() != TokenType.END );

        return current; // original was return current

    }

    /**
     * Process an AND expression and return a node that represents the
     * root of the tree for the AND expression.
     * 
     * This method determines if the next unprocessed portion of the query
     * is an AND expression or not. If it is then it creates a tree that
     * represents
     * the AND expression. This tree typically become a subtree of the AST for
     * the
     * complete query.
     * 
     * @return the root of the tree that represents an AND expression.
     */
    protected ASTNode andExpr() { // this one has been completed
        ASTNode current;

        ASTNode leftTree = atom();
        current = leftTree;

        Token nextToken;

        do {
            nextToken = peek();

            if ( nextToken.getType() == TokenType.AND ) {
                consume();
                ASTNode head = new AndNode( Token.getTokenInstance( nextToken
                        .getPayload() ) );
                head.addChild( current );
                ASTNode rightTree = atom();
                head.addChild( rightTree );
                current = head;

            } else {
                return current;
            }

        } while ( nextToken.getType() != TokenType.END );

        return current;

    }

    /**
     * Process an atomic expression.
     * 
     * @return the root of a tree that represents the atomic expression.
     */
    protected ASTNode atom() {
        Token nextToken = consume();

        if ( nextToken.getType() == TokenType.END ) {
            return null;
        }

        if ( nextToken.getType() == TokenType.L_PARAN ) {
            // Process compound expression
            ASTNode tree = orExpr();
            consume(); // remove RPAREN
            return tree;
        } else {
            return processLeaveNodes( nextToken );
        }

    }

    /**
     * Process the leaf nodes of the AST.
     * 
     * @return a leaf node of the AST.
     */
    @SuppressWarnings("rawtypes")
    private ASTNode processLeaveNodes( Token token ) {
        Class<? extends ASTNode> astClass = map.get( token.getType() );
        Class[] parameters = new Class[] { Token.class };
        try {
            Constructor cons = astClass.getConstructor( parameters );
            Object[] arguments = new Object[] { token };
            ASTNode ast = (ASTNode) cons.newInstance( arguments );

            consume(); // remove LPAREN
            Token argument = consume();
            ast.setArguments( sanitizeString( argument ) );
            consume(); // remove RPAREN

            return ast;

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    // "Some string" -> Some string (quotes removed)
    String sanitizeString( Token argument ) { // changed from private to default
        String payload = argument.getPayload();
        payload = payload.substring( 1, payload.length() - 1 );
        return payload;
    }

    // method to obtain the next token to process
    private Token consume() {
        if ( currentPosition == tokenStream.size() )
            return END_TOKEN;

        return tokenStream.get( currentPosition++ );
    }

    // method to look at the next token to process
    public Token peek() {
        if ( currentPosition == tokenStream.size() )
            return END_TOKEN;

        return tokenStream.get( currentPosition );
    }
}
