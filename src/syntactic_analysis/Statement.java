package syntactic_analysis;

import java.util.List;

import interpretation.Interpreter;
import lexical_analysis.Token;

public abstract class Statement {
	
	public interface Visitor<T> {
		T visitExpressionStatement(ExpressionStatement statement);
		T visitPrintStatement(Print statement);
		T visitVariableDeclarationStatement(VariableDeclaration statement);
		T visitBlockStatement(Block statement);
		T visitFunctionStatement(Function statement);
		T visitIfStatement(If statement);
		T visitWhileStatement(While statement);
		T visitReturnStatement(Return statement);
		T visitReadStatement(Read statement);
		
	}
	
	public static class Read extends Statement {
		
		public final Expression expression;
		
		Read(Expression e) {
			this.expression = e;
		}
		
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitReadStatement(this);
		}
		
	}

	public static class Return extends Statement {
		// Fields.
		public final Token symbol;
		public final Expression value;
		
		public Return(Token symbol, Expression value) {
			this.symbol = symbol;
			this.value = value;
		}

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitReturnStatement(this);
		}
		
	}
	
	public static class Block extends Statement {
		
		public final List<Statement> statements;
		
		Block(List<Statement> statements) {
			this.statements = statements;
		}

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitBlockStatement(this);
		}
	}
	
	public static class ExpressionStatement extends Statement {
		
		public final Expression expression;
		
		ExpressionStatement(Expression e) {
			this.expression = e;
		}
		
	    public <T> T accept(Visitor<T> visitor) {
	        return visitor.visitExpressionStatement(this);
	      }
	    
	}

	public static class Function extends Statement {

		public final Token symbol;
		public final List<Token> arguments;
		public final List<Statement> functionBlock;

		public Function(Token symbol, List<Token> arguments, List<Statement> functionBlock) {
			this.symbol = symbol;
			this.arguments = arguments;
			this.functionBlock = functionBlock;
		}

		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitFunctionStatement(this);
		}

	}

	public static class Print extends Statement {
		
		public final Expression expression;
		
		Print(Expression e) {
			this.expression = e;
		}

		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitPrintStatement(this);
		}
		
	}
	
	public static class If extends Statement {
		// fields
		public final Expression condition;
		public final Statement thenBranch;
		public final Statement elseBranch;

		If(Expression condition, Statement thenBranch, Statement elseBranch) {
			this.condition = condition;
			this.thenBranch = thenBranch;
			this.elseBranch = elseBranch;
		}

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitIfStatement(this);
		}

	}

	public static class While extends Statement {
		// fields
		public final Expression condition;
		public final Statement body;

		public While(Expression condition, Statement body) {
			this.condition = condition;
			this.body = body;
		}

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitWhileStatement(this);
		}

	}
	
	public static class VariableDeclaration extends Statement {

		public final Token symbol;
		public final Expression initialisation;

		VariableDeclaration(Token symbol, Expression initialisation) {
			this.symbol = symbol;
			this.initialisation = initialisation;
		}

		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitVariableDeclarationStatement(this);
		}

	}

	public abstract <T> T accept(Visitor<T> visitor);
	
}
