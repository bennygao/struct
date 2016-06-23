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
		WS=30, IGNORED_COMMENT=31, SL_COMMENT=32, COMMENT=33;
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
		null, "'import'", "';'", "'struct'", "'{'", "'}'", "'='", "'+'", "'-'", 
		"'byte'", "'int8'", "'uint8'", "'short'", "'int16'", "'uint16'", "'int'", 
		"'int32'", "'uint32'", "'int64'", "'uint64'", "'float'", "'double'", "'string'", 
		"'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "Identifier", "DecimalLiteral", "HexLiteral", "StringLiteral", "FloatingPointLiteral", 
		"WS", "IGNORED_COMMENT", "SL_COMMENT", "COMMENT"
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
			} while ( _la==T__2 );
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
			setState(45);
			match(T__1);
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
			setState(47);
			match(T__2);
			setState(48);
			match(Identifier);
			setState(49);
			match(T__3);
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				field();
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << Identifier))) != 0) );
			setState(55);
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
			setState(57);
			type();
			setState(58);
			match(Identifier);
			setState(60);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(59);
				defaultValue();
				}
			}

			setState(62);
			match(T__1);
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
			setState(75);
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
				setState(64);
				basicType();
				setState(66);
				_la = _input.LA(1);
				if (_la==T__22) {
					{
					setState(65);
					fixedArray();
					}
				}

				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				stringType();
				setState(69);
				fixedArray();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				structType();
				setState(73);
				_la = _input.LA(1);
				if (_la==T__22) {
					{
					setState(72);
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
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				numberDefaultValue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				stringDefaultValue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
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
			setState(82);
			match(T__5);
			setState(83);
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
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				match(T__5);
				setState(86);
				match(HexLiteral);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				match(T__5);
				setState(88);
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
			setState(92);
			_la = _input.LA(1);
			if (_la==T__6 || _la==T__7) {
				{
				setState(91);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__7) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(94);
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
			setState(96);
			match(T__5);
			setState(97);
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
			setState(99);
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
			setState(101);
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
			setState(103);
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
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				fixedArray();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
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
			setState(109);
			match(T__22);
			setState(110);
			match(DecimalLiteral);
			setState(111);
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
			setState(113);
			match(T__22);
			setState(114);
			match(Identifier);
			setState(115);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#x\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2$\n\2\f\2"+
		"\16\2\'\13\2\3\2\6\2*\n\2\r\2\16\2+\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\6"+
		"\4\66\n\4\r\4\16\4\67\3\4\3\4\3\5\3\5\3\5\5\5?\n\5\3\5\3\5\3\6\3\6\5\6"+
		"E\n\6\3\6\3\6\3\6\3\6\3\6\5\6L\n\6\5\6N\n\6\3\7\3\7\3\7\5\7S\n\7\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\5\t\\\n\t\3\n\5\n_\n\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\5\17n\n\17\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2"+
		"\4\3\2\t\n\3\2\13\27t\2%\3\2\2\2\4-\3\2\2\2\6\61\3\2\2\2\b;\3\2\2\2\n"+
		"M\3\2\2\2\fR\3\2\2\2\16T\3\2\2\2\20[\3\2\2\2\22^\3\2\2\2\24b\3\2\2\2\26"+
		"e\3\2\2\2\30g\3\2\2\2\32i\3\2\2\2\34m\3\2\2\2\36o\3\2\2\2 s\3\2\2\2\""+
		"$\5\4\3\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&)\3\2\2\2\'%\3\2\2"+
		"\2(*\5\6\4\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\3\3\2\2\2-.\7\3"+
		"\2\2./\7\36\2\2/\60\7\4\2\2\60\5\3\2\2\2\61\62\7\5\2\2\62\63\7\33\2\2"+
		"\63\65\7\6\2\2\64\66\5\b\5\2\65\64\3\2\2\2\66\67\3\2\2\2\67\65\3\2\2\2"+
		"\678\3\2\2\289\3\2\2\29:\7\7\2\2:\7\3\2\2\2;<\5\n\6\2<>\7\33\2\2=?\5\f"+
		"\7\2>=\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\7\4\2\2A\t\3\2\2\2BD\5\26\f\2CE\5"+
		"\36\20\2DC\3\2\2\2DE\3\2\2\2EN\3\2\2\2FG\5\30\r\2GH\5\36\20\2HN\3\2\2"+
		"\2IK\5\32\16\2JL\5\34\17\2KJ\3\2\2\2KL\3\2\2\2LN\3\2\2\2MB\3\2\2\2MF\3"+
		"\2\2\2MI\3\2\2\2N\13\3\2\2\2OS\5\20\t\2PS\5\24\13\2QS\5\16\b\2RO\3\2\2"+
		"\2RP\3\2\2\2RQ\3\2\2\2S\r\3\2\2\2TU\7\b\2\2UV\7\37\2\2V\17\3\2\2\2WX\7"+
		"\b\2\2X\\\7\35\2\2YZ\7\b\2\2Z\\\5\22\n\2[W\3\2\2\2[Y\3\2\2\2\\\21\3\2"+
		"\2\2]_\t\2\2\2^]\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\7\34\2\2a\23\3\2\2\2bc"+
		"\7\b\2\2cd\7\36\2\2d\25\3\2\2\2ef\t\3\2\2f\27\3\2\2\2gh\7\30\2\2h\31\3"+
		"\2\2\2ij\7\33\2\2j\33\3\2\2\2kn\5\36\20\2ln\5 \21\2mk\3\2\2\2ml\3\2\2"+
		"\2n\35\3\2\2\2op\7\31\2\2pq\7\34\2\2qr\7\32\2\2r\37\3\2\2\2st\7\31\2\2"+
		"tu\7\33\2\2uv\7\32\2\2v!\3\2\2\2\r%+\67>DKMR[^m";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}