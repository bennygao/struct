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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, Identifier=31, 
		DecimalLiteral=32, HexLiteral=33, StringLiteral=34, FloatingPointLiteral=35, 
		WS=36, IGNORED_COMMENT=37, SL_COMMENT=38, COMMENT=39;
	public static final int
		RULE_prog = 0, RULE_include = 1, RULE_clazz = 2, RULE_bitfield = 3, RULE_bits = 4, 
		RULE_struct = 5, RULE_field = 6, RULE_type = 7, RULE_defaultValue = 8, 
		RULE_floatDefaultValue = 9, RULE_numberDefaultValue = 10, RULE_decimalValue = 11, 
		RULE_stringDefaultValue = 12, RULE_basicType = 13, RULE_stringType = 14, 
		RULE_structType = 15, RULE_genericType = 16, RULE_array = 17, RULE_varlenArray = 18, 
		RULE_fixedArray = 19, RULE_identifierArray = 20;
	public static final String[] ruleNames = {
		"prog", "include", "clazz", "bitfield", "bits", "struct", "field", "type", 
		"defaultValue", "floatDefaultValue", "numberDefaultValue", "decimalValue", 
		"stringDefaultValue", "basicType", "stringType", "structType", "genericType", 
		"array", "varlenArray", "fixedArray", "identifierArray"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'import'", "';'", "'bitfield'", "'{'", "'}'", "':'", "'struct'", 
		"'='", "'+'", "'-'", "'byte'", "'int8'", "'uint8'", "'short'", "'int16'", 
		"'uint16'", "'int'", "'int32'", "'uint32'", "'long'", "'int64'", "'uint64'", 
		"'float'", "'double'", "'string'", "'Struct'", "'('", "')'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "Identifier", "DecimalLiteral", 
		"HexLiteral", "StringLiteral", "FloatingPointLiteral", "WS", "IGNORED_COMMENT", 
		"SL_COMMENT", "COMMENT"
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
		public List<ClazzContext> clazz() {
			return getRuleContexts(ClazzContext.class);
		}
		public ClazzContext clazz(int i) {
			return getRuleContext(ClazzContext.class,i);
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
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(42);
				include();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				clazz();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__6 );
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
			setState(53);
			match(T__0);
			setState(54);
			match(StringLiteral);
			setState(55);
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

	public static class ClazzContext extends ParserRuleContext {
		public StructContext struct() {
			return getRuleContext(StructContext.class,0);
		}
		public BitfieldContext bitfield() {
			return getRuleContext(BitfieldContext.class,0);
		}
		public ClazzContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clazz; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterClazz(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitClazz(this);
		}
	}

	public final ClazzContext clazz() throws RecognitionException {
		ClazzContext _localctx = new ClazzContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_clazz);
		try {
			setState(59);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				struct();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				bitfield();
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

	public static class BitfieldContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(StructParser.Identifier, 0); }
		public List<BitsContext> bits() {
			return getRuleContexts(BitsContext.class);
		}
		public BitsContext bits(int i) {
			return getRuleContext(BitsContext.class,i);
		}
		public BitfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterBitfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitBitfield(this);
		}
	}

	public final BitfieldContext bitfield() throws RecognitionException {
		BitfieldContext _localctx = new BitfieldContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bitfield);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__2);
			setState(62);
			match(Identifier);
			setState(63);
			match(T__3);
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				bits();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Identifier );
			setState(69);
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

	public static class BitsContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(StructParser.Identifier, 0); }
		public TerminalNode DecimalLiteral() { return getToken(StructParser.DecimalLiteral, 0); }
		public NumberDefaultValueContext numberDefaultValue() {
			return getRuleContext(NumberDefaultValueContext.class,0);
		}
		public BitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bits; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterBits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitBits(this);
		}
	}

	public final BitsContext bits() throws RecognitionException {
		BitsContext _localctx = new BitsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bits);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(Identifier);
			setState(72);
			match(T__5);
			setState(73);
			match(DecimalLiteral);
			setState(75);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(74);
				numberDefaultValue();
				}
			}

			setState(77);
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
		enterRule(_localctx, 10, RULE_struct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__6);
			setState(80);
			match(Identifier);
			setState(81);
			match(T__3);
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				field();
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << Identifier))) != 0) );
			setState(87);
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
		enterRule(_localctx, 12, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			type();
			setState(90);
			match(Identifier);
			setState(92);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(91);
				defaultValue();
				}
			}

			setState(94);
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
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public StringTypeContext stringType() {
			return getRuleContext(StringTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public ClazzContext clazz() {
			return getRuleContext(ClazzContext.class,0);
		}
		public GenericTypeContext genericType() {
			return getRuleContext(GenericTypeContext.class,0);
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
		enterRule(_localctx, 14, RULE_type);
		int _la;
		try {
			setState(115);
			switch (_input.LA(1)) {
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
			case T__21:
			case T__22:
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				basicType();
				setState(98);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(97);
					array();
					}
				}

				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				stringType();
				setState(101);
				array();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 3);
				{
				setState(103);
				structType();
				setState(105);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(104);
					array();
					}
				}

				}
				break;
			case T__2:
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(107);
				clazz();
				setState(109);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(108);
					array();
					}
				}

				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 5);
				{
				setState(111);
				genericType();
				setState(113);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(112);
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
		enterRule(_localctx, 16, RULE_defaultValue);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				numberDefaultValue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				stringDefaultValue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
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
		enterRule(_localctx, 18, RULE_floatDefaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__7);
			setState(123);
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
		enterRule(_localctx, 20, RULE_numberDefaultValue);
		try {
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				match(T__7);
				setState(126);
				match(HexLiteral);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				match(T__7);
				setState(128);
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
		enterRule(_localctx, 22, RULE_decimalValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_la = _input.LA(1);
			if (_la==T__8 || _la==T__9) {
				{
				setState(131);
				_la = _input.LA(1);
				if ( !(_la==T__8 || _la==T__9) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(134);
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
		enterRule(_localctx, 24, RULE_stringDefaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__7);
			setState(137);
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
		enterRule(_localctx, 26, RULE_basicType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) ) {
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
		enterRule(_localctx, 28, RULE_stringType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__24);
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
		enterRule(_localctx, 30, RULE_structType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
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

	public static class GenericTypeContext extends ParserRuleContext {
		public TerminalNode DecimalLiteral() { return getToken(StructParser.DecimalLiteral, 0); }
		public GenericTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterGenericType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitGenericType(this);
		}
	}

	public final GenericTypeContext genericType() throws RecognitionException {
		GenericTypeContext _localctx = new GenericTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_genericType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__25);
			setState(146);
			match(T__26);
			setState(147);
			match(DecimalLiteral);
			setState(148);
			match(T__27);
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
		public VarlenArrayContext varlenArray() {
			return getRuleContext(VarlenArrayContext.class,0);
		}
		public FixedArrayContext fixedArray() {
			return getRuleContext(FixedArrayContext.class,0);
		}
		public IdentifierArrayContext identifierArray() {
			return getRuleContext(IdentifierArrayContext.class,0);
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
		enterRule(_localctx, 34, RULE_array);
		try {
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				varlenArray();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				fixedArray();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				identifierArray();
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

	public static class VarlenArrayContext extends ParserRuleContext {
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
		enterRule(_localctx, 36, RULE_varlenArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__28);
			setState(156);
			match(T__29);
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
		enterRule(_localctx, 38, RULE_fixedArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__28);
			setState(159);
			match(DecimalLiteral);
			setState(160);
			match(T__29);
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

	public static class IdentifierArrayContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(StructParser.Identifier, 0); }
		public IdentifierArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterIdentifierArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitIdentifierArray(this);
		}
	}

	public final IdentifierArrayContext identifierArray() throws RecognitionException {
		IdentifierArrayContext _localctx = new IdentifierArrayContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_identifierArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__28);
			setState(163);
			match(Identifier);
			setState(164);
			match(T__29);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3)\u00a9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\7\2.\n\2\f\2\16\2\61\13\2"+
		"\3\2\6\2\64\n\2\r\2\16\2\65\3\3\3\3\3\3\3\3\3\4\3\4\5\4>\n\4\3\5\3\5\3"+
		"\5\3\5\6\5D\n\5\r\5\16\5E\3\5\3\5\3\6\3\6\3\6\3\6\5\6N\n\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\6\7V\n\7\r\7\16\7W\3\7\3\7\3\b\3\b\3\b\5\b_\n\b\3\b\3\b\3"+
		"\t\3\t\5\te\n\t\3\t\3\t\3\t\3\t\3\t\5\tl\n\t\3\t\3\t\5\tp\n\t\3\t\3\t"+
		"\5\tt\n\t\5\tv\n\t\3\n\3\n\3\n\5\n{\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\5\f\u0084\n\f\3\r\5\r\u0087\n\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\5\23\u009c\n\23"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\2\2\27\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\4\3\2\13\f\3\2\r\32\u00a8"+
		"\2/\3\2\2\2\4\67\3\2\2\2\6=\3\2\2\2\b?\3\2\2\2\nI\3\2\2\2\fQ\3\2\2\2\16"+
		"[\3\2\2\2\20u\3\2\2\2\22z\3\2\2\2\24|\3\2\2\2\26\u0083\3\2\2\2\30\u0086"+
		"\3\2\2\2\32\u008a\3\2\2\2\34\u008d\3\2\2\2\36\u008f\3\2\2\2 \u0091\3\2"+
		"\2\2\"\u0093\3\2\2\2$\u009b\3\2\2\2&\u009d\3\2\2\2(\u00a0\3\2\2\2*\u00a4"+
		"\3\2\2\2,.\5\4\3\2-,\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\63\3"+
		"\2\2\2\61/\3\2\2\2\62\64\5\6\4\2\63\62\3\2\2\2\64\65\3\2\2\2\65\63\3\2"+
		"\2\2\65\66\3\2\2\2\66\3\3\2\2\2\678\7\3\2\289\7$\2\29:\7\4\2\2:\5\3\2"+
		"\2\2;>\5\f\7\2<>\5\b\5\2=;\3\2\2\2=<\3\2\2\2>\7\3\2\2\2?@\7\5\2\2@A\7"+
		"!\2\2AC\7\6\2\2BD\5\n\6\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FG\3"+
		"\2\2\2GH\7\7\2\2H\t\3\2\2\2IJ\7!\2\2JK\7\b\2\2KM\7\"\2\2LN\5\26\f\2ML"+
		"\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\7\4\2\2P\13\3\2\2\2QR\7\t\2\2RS\7!\2\2"+
		"SU\7\6\2\2TV\5\16\b\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2XY\3\2\2"+
		"\2YZ\7\7\2\2Z\r\3\2\2\2[\\\5\20\t\2\\^\7!\2\2]_\5\22\n\2^]\3\2\2\2^_\3"+
		"\2\2\2_`\3\2\2\2`a\7\4\2\2a\17\3\2\2\2bd\5\34\17\2ce\5$\23\2dc\3\2\2\2"+
		"de\3\2\2\2ev\3\2\2\2fg\5\36\20\2gh\5$\23\2hv\3\2\2\2ik\5 \21\2jl\5$\23"+
		"\2kj\3\2\2\2kl\3\2\2\2lv\3\2\2\2mo\5\6\4\2np\5$\23\2on\3\2\2\2op\3\2\2"+
		"\2pv\3\2\2\2qs\5\"\22\2rt\5$\23\2sr\3\2\2\2st\3\2\2\2tv\3\2\2\2ub\3\2"+
		"\2\2uf\3\2\2\2ui\3\2\2\2um\3\2\2\2uq\3\2\2\2v\21\3\2\2\2w{\5\26\f\2x{"+
		"\5\32\16\2y{\5\24\13\2zw\3\2\2\2zx\3\2\2\2zy\3\2\2\2{\23\3\2\2\2|}\7\n"+
		"\2\2}~\7%\2\2~\25\3\2\2\2\177\u0080\7\n\2\2\u0080\u0084\7#\2\2\u0081\u0082"+
		"\7\n\2\2\u0082\u0084\5\30\r\2\u0083\177\3\2\2\2\u0083\u0081\3\2\2\2\u0084"+
		"\27\3\2\2\2\u0085\u0087\t\2\2\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2"+
		"\2\u0087\u0088\3\2\2\2\u0088\u0089\7\"\2\2\u0089\31\3\2\2\2\u008a\u008b"+
		"\7\n\2\2\u008b\u008c\7$\2\2\u008c\33\3\2\2\2\u008d\u008e\t\3\2\2\u008e"+
		"\35\3\2\2\2\u008f\u0090\7\33\2\2\u0090\37\3\2\2\2\u0091\u0092\7!\2\2\u0092"+
		"!\3\2\2\2\u0093\u0094\7\34\2\2\u0094\u0095\7\35\2\2\u0095\u0096\7\"\2"+
		"\2\u0096\u0097\7\36\2\2\u0097#\3\2\2\2\u0098\u009c\5&\24\2\u0099\u009c"+
		"\5(\25\2\u009a\u009c\5*\26\2\u009b\u0098\3\2\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009a\3\2\2\2\u009c%\3\2\2\2\u009d\u009e\7\37\2\2\u009e\u009f\7 \2\2"+
		"\u009f\'\3\2\2\2\u00a0\u00a1\7\37\2\2\u00a1\u00a2\7\"\2\2\u00a2\u00a3"+
		"\7 \2\2\u00a3)\3\2\2\2\u00a4\u00a5\7\37\2\2\u00a5\u00a6\7!\2\2\u00a6\u00a7"+
		"\7 \2\2\u00a7+\3\2\2\2\22/\65=EMW^dkosuz\u0083\u0086\u009b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}