// Generated from Struct.g4 by ANTLR 4.5.3
package cc.devfun.struct.compiler.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StructParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		Identifier=25, DecimalLiteral=26, HexLiteral=27, StringLiteral=28, FloatingPointLiteral=29, 
		WS=30, SL_COMMENT=31, COMMENT=32;
	public static final int
		RULE_prog = 0, RULE_include = 1, RULE_struct = 2, RULE_field = 3, RULE_type = 4, 
		RULE_defaultValue = 5, RULE_floatDefaultValue = 6, RULE_numberDefaultValue = 7, 
		RULE_decimalValue = 8, RULE_stringDefaultValue = 9, RULE_basicType = 10, 
		RULE_stringType = 11, RULE_structType = 12, RULE_array = 13, RULE_fixedArray = 14, 
		RULE_varlenArray = 15;
	public static final String[] ruleNames = {
		"prog", "include", "struct", "field", "type", "defaultValue", "floatDefaultValue", 
		"numberDefaultValue", "decimalValue", "stringDefaultValue", "basicType", 
		"stringType", "structType", "array", "fixedArray", "varlenArray"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'#include'", "'struct'", "'{'", "'}'", "';'", "'='", "'+'", "'-'", 
		"'byte'", "'int8'", "'uint8'", "'short'", "'int16'", "'uint16'", "'int'", 
		"'int32'", "'uint32'", "'int64'", "'uint64'", "'float'", "'double'", "'string'", 
		"'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "Identifier", "DecimalLiteral", "HexLiteral", "StringLiteral", "FloatingPointLiteral", 
		"WS", "SL_COMMENT", "COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Struct.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public StructParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public List<IncludeContext> include() {
			return getRuleContexts(IncludeContext.class);
		}
		public IncludeContext include(int i) {
			return getRuleContext(IncludeContext.class,i);
		}
		public List<StructContext> struct() {
			return getRuleContexts(StructContext.class);
		}
		public StructContext struct(int i) {
			return getRuleContext(StructContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(32);
				include();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				struct();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IncludeContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(StructParser.StringLiteral, 0); }
		public IncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_include; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterInclude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitInclude(this);
		}
	}

	public final IncludeContext include() throws RecognitionException {
		IncludeContext _localctx = new IncludeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_include);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(T__0);
			setState(44);
			match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(StructParser.Identifier, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public StructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitStruct(this);
		}
	}

	public final StructContext struct() throws RecognitionException {
		StructContext _localctx = new StructContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_struct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__1);
			setState(47);
			match(Identifier);
			setState(48);
			match(T__2);
			setState(50); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(49);
				field();
				}
				}
				setState(52); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << Identifier))) != 0) );
			setState(54);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(StructParser.Identifier, 0); }
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			type();
			setState(57);
			match(Identifier);
			setState(59);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(58);
				defaultValue();
				}
			}

			setState(61);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public FixedArrayContext fixedArray() {
			return getRuleContext(FixedArrayContext.class,0);
		}
		public StringTypeContext stringType() {
			return getRuleContext(StringTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		int _la;
		try {
			setState(74);
			switch (_input.LA(1)) {
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				basicType();
				setState(65);
				_la = _input.LA(1);
				if (_la==T__22) {
					{
					setState(64);
					fixedArray();
					}
				}

				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				stringType();
				setState(68);
				fixedArray();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				structType();
				setState(72);
				_la = _input.LA(1);
				if (_la==T__22) {
					{
					setState(71);
					array();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public NumberDefaultValueContext numberDefaultValue() {
			return getRuleContext(NumberDefaultValueContext.class,0);
		}
		public StringDefaultValueContext stringDefaultValue() {
			return getRuleContext(StringDefaultValueContext.class,0);
		}
		public FloatDefaultValueContext floatDefaultValue() {
			return getRuleContext(FloatDefaultValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defaultValue);
		try {
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				numberDefaultValue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				stringDefaultValue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				floatDefaultValue();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatDefaultValueContext extends ParserRuleContext {
		public TerminalNode FloatingPointLiteral() { return getToken(StructParser.FloatingPointLiteral, 0); }
		public FloatDefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatDefaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterFloatDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitFloatDefaultValue(this);
		}
	}

	public final FloatDefaultValueContext floatDefaultValue() throws RecognitionException {
		FloatDefaultValueContext _localctx = new FloatDefaultValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_floatDefaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(T__5);
			setState(82);
			match(FloatingPointLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberDefaultValueContext extends ParserRuleContext {
		public TerminalNode HexLiteral() { return getToken(StructParser.HexLiteral, 0); }
		public DecimalValueContext decimalValue() {
			return getRuleContext(DecimalValueContext.class,0);
		}
		public NumberDefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberDefaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterNumberDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitNumberDefaultValue(this);
		}
	}

	public final NumberDefaultValueContext numberDefaultValue() throws RecognitionException {
		NumberDefaultValueContext _localctx = new NumberDefaultValueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numberDefaultValue);
		try {
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				match(T__5);
				setState(85);
				match(HexLiteral);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				match(T__5);
				setState(87);
				decimalValue();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecimalValueContext extends ParserRuleContext {
		public TerminalNode DecimalLiteral() { return getToken(StructParser.DecimalLiteral, 0); }
		public DecimalValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimalValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterDecimalValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitDecimalValue(this);
		}
	}

	public final DecimalValueContext decimalValue() throws RecognitionException {
		DecimalValueContext _localctx = new DecimalValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_decimalValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_la = _input.LA(1);
			if (_la==T__6 || _la==T__7) {
				{
				setState(90);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__7) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(93);
			match(DecimalLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringDefaultValueContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(StructParser.StringLiteral, 0); }
		public StringDefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringDefaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterStringDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitStringDefaultValue(this);
		}
	}

	public final StringDefaultValueContext stringDefaultValue() throws RecognitionException {
		StringDefaultValueContext _localctx = new StringDefaultValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stringDefaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__5);
			setState(96);
			match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasicTypeContext extends ParserRuleContext {
		public BasicTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterBasicType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitBasicType(this);
		}
	}

	public final BasicTypeContext basicType() throws RecognitionException {
		BasicTypeContext _localctx = new BasicTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_basicType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringTypeContext extends ParserRuleContext {
		public StringTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterStringType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitStringType(this);
		}
	}

	public final StringTypeContext stringType() throws RecognitionException {
		StringTypeContext _localctx = new StringTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_stringType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(StructParser.Identifier, 0); }
		public StructTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterStructType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitStructType(this);
		}
	}

	public final StructTypeContext structType() throws RecognitionException {
		StructTypeContext _localctx = new StructTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_structType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public FixedArrayContext fixedArray() {
			return getRuleContext(FixedArrayContext.class,0);
		}
		public VarlenArrayContext varlenArray() {
			return getRuleContext(VarlenArrayContext.class,0);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_array);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				fixedArray();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				varlenArray();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FixedArrayContext extends ParserRuleContext {
		public TerminalNode DecimalLiteral() { return getToken(StructParser.DecimalLiteral, 0); }
		public FixedArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixedArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterFixedArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitFixedArray(this);
		}
	}

	public final FixedArrayContext fixedArray() throws RecognitionException {
		FixedArrayContext _localctx = new FixedArrayContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fixedArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__22);
			setState(109);
			match(DecimalLiteral);
			setState(110);
			match(T__23);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarlenArrayContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(StructParser.Identifier, 0); }
		public VarlenArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varlenArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterVarlenArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitVarlenArray(this);
		}
	}

	public final VarlenArrayContext varlenArray() throws RecognitionException {
		VarlenArrayContext _localctx = new VarlenArrayContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_varlenArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__22);
			setState(113);
			match(Identifier);
			setState(114);
			match(T__23);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"w\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2$\n\2\f"+
		"\2\16\2\'\13\2\3\2\6\2*\n\2\r\2\16\2+\3\3\3\3\3\3\3\4\3\4\3\4\3\4\6\4"+
		"\65\n\4\r\4\16\4\66\3\4\3\4\3\5\3\5\3\5\5\5>\n\5\3\5\3\5\3\6\3\6\5\6D"+
		"\n\6\3\6\3\6\3\6\3\6\3\6\5\6K\n\6\5\6M\n\6\3\7\3\7\3\7\5\7R\n\7\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\5\t[\n\t\3\n\5\n^\n\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\5\17m\n\17\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2"+
		"\4\3\2\t\n\3\2\13\27s\2%\3\2\2\2\4-\3\2\2\2\6\60\3\2\2\2\b:\3\2\2\2\n"+
		"L\3\2\2\2\fQ\3\2\2\2\16S\3\2\2\2\20Z\3\2\2\2\22]\3\2\2\2\24a\3\2\2\2\26"+
		"d\3\2\2\2\30f\3\2\2\2\32h\3\2\2\2\34l\3\2\2\2\36n\3\2\2\2 r\3\2\2\2\""+
		"$\5\4\3\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&)\3\2\2\2\'%\3\2\2"+
		"\2(*\5\6\4\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\3\3\2\2\2-.\7\3"+
		"\2\2./\7\36\2\2/\5\3\2\2\2\60\61\7\4\2\2\61\62\7\33\2\2\62\64\7\5\2\2"+
		"\63\65\5\b\5\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2"+
		"\678\3\2\2\289\7\6\2\29\7\3\2\2\2:;\5\n\6\2;=\7\33\2\2<>\5\f\7\2=<\3\2"+
		"\2\2=>\3\2\2\2>?\3\2\2\2?@\7\7\2\2@\t\3\2\2\2AC\5\26\f\2BD\5\36\20\2C"+
		"B\3\2\2\2CD\3\2\2\2DM\3\2\2\2EF\5\30\r\2FG\5\36\20\2GM\3\2\2\2HJ\5\32"+
		"\16\2IK\5\34\17\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LA\3\2\2\2LE\3\2\2\2LH"+
		"\3\2\2\2M\13\3\2\2\2NR\5\20\t\2OR\5\24\13\2PR\5\16\b\2QN\3\2\2\2QO\3\2"+
		"\2\2QP\3\2\2\2R\r\3\2\2\2ST\7\b\2\2TU\7\37\2\2U\17\3\2\2\2VW\7\b\2\2W"+
		"[\7\35\2\2XY\7\b\2\2Y[\5\22\n\2ZV\3\2\2\2ZX\3\2\2\2[\21\3\2\2\2\\^\t\2"+
		"\2\2]\\\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\34\2\2`\23\3\2\2\2ab\7\b\2\2b"+
		"c\7\36\2\2c\25\3\2\2\2de\t\3\2\2e\27\3\2\2\2fg\7\30\2\2g\31\3\2\2\2hi"+
		"\7\33\2\2i\33\3\2\2\2jm\5\36\20\2km\5 \21\2lj\3\2\2\2lk\3\2\2\2m\35\3"+
		"\2\2\2no\7\31\2\2op\7\34\2\2pq\7\32\2\2q\37\3\2\2\2rs\7\31\2\2st\7\33"+
		"\2\2tu\7\32\2\2u!\3\2\2\2\r%+\66=CJLQZ]l";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}