// Generated from Struct.g4 by ANTLR 4.5.3
package cc.devfun.struct.compiler.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StructParser}.
 */
public interface StructListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StructParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(StructParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(StructParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#include}.
	 * @param ctx the parse tree
	 */
	void enterInclude(StructParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#include}.
	 * @param ctx the parse tree
	 */
	void exitInclude(StructParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#clazz}.
	 * @param ctx the parse tree
	 */
	void enterClazz(StructParser.ClazzContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#clazz}.
	 * @param ctx the parse tree
	 */
	void exitClazz(StructParser.ClazzContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#bitfield}.
	 * @param ctx the parse tree
	 */
	void enterBitfield(StructParser.BitfieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#bitfield}.
	 * @param ctx the parse tree
	 */
	void exitBitfield(StructParser.BitfieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#bits}.
	 * @param ctx the parse tree
	 */
	void enterBits(StructParser.BitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#bits}.
	 * @param ctx the parse tree
	 */
	void exitBits(StructParser.BitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(StructParser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(StructParser.StructContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(StructParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(StructParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(StructParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(StructParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(StructParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(StructParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#floatDefaultValue}.
	 * @param ctx the parse tree
	 */
	void enterFloatDefaultValue(StructParser.FloatDefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#floatDefaultValue}.
	 * @param ctx the parse tree
	 */
	void exitFloatDefaultValue(StructParser.FloatDefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#numberDefaultValue}.
	 * @param ctx the parse tree
	 */
	void enterNumberDefaultValue(StructParser.NumberDefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#numberDefaultValue}.
	 * @param ctx the parse tree
	 */
	void exitNumberDefaultValue(StructParser.NumberDefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#decimalValue}.
	 * @param ctx the parse tree
	 */
	void enterDecimalValue(StructParser.DecimalValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#decimalValue}.
	 * @param ctx the parse tree
	 */
	void exitDecimalValue(StructParser.DecimalValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#stringDefaultValue}.
	 * @param ctx the parse tree
	 */
	void enterStringDefaultValue(StructParser.StringDefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#stringDefaultValue}.
	 * @param ctx the parse tree
	 */
	void exitStringDefaultValue(StructParser.StringDefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#basicType}.
	 * @param ctx the parse tree
	 */
	void enterBasicType(StructParser.BasicTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#basicType}.
	 * @param ctx the parse tree
	 */
	void exitBasicType(StructParser.BasicTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#stringType}.
	 * @param ctx the parse tree
	 */
	void enterStringType(StructParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#stringType}.
	 * @param ctx the parse tree
	 */
	void exitStringType(StructParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#structType}.
	 * @param ctx the parse tree
	 */
	void enterStructType(StructParser.StructTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#structType}.
	 * @param ctx the parse tree
	 */
	void exitStructType(StructParser.StructTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#arrayOfStruct}.
	 * @param ctx the parse tree
	 */
	void enterArrayOfStruct(StructParser.ArrayOfStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#arrayOfStruct}.
	 * @param ctx the parse tree
	 */
	void exitArrayOfStruct(StructParser.ArrayOfStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#varlenArray}.
	 * @param ctx the parse tree
	 */
	void enterVarlenArray(StructParser.VarlenArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#varlenArray}.
	 * @param ctx the parse tree
	 */
	void exitVarlenArray(StructParser.VarlenArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#fixedArray}.
	 * @param ctx the parse tree
	 */
	void enterFixedArray(StructParser.FixedArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#fixedArray}.
	 * @param ctx the parse tree
	 */
	void exitFixedArray(StructParser.FixedArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link StructParser#identifierArray}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierArray(StructParser.IdentifierArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link StructParser#identifierArray}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierArray(StructParser.IdentifierArrayContext ctx);
}