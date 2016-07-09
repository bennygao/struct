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
		T__24=25, T__25=26, T__26=27, Identifier=28, DecimalLiteral=29, HexLiteral=30, 
		StringLiteral=31, FloatingPointLiteral=32, WS=33, IGNORED_COMMENT=34, 
		SL_COMMENT=35, COMMENT=36;
	public static final int
		RULE_prog = 0, RULE_include = 1, RULE_clazz = 2, RULE_bitfield = 3, RULE_bits = 4, 
		RULE_struct = 5, RULE_field = 6, RULE_type = 7, RULE_defaultValue = 8, 
		RULE_floatDefaultValue = 9, RULE_numberDefaultValue = 10, RULE_decimalValue = 11, 
		RULE_stringDefaultValue = 12, RULE_basicType = 13, RULE_stringType = 14, 
		RULE_structType = 15, RULE_arrayOfStruct = 16, RULE_varlenArray = 17, 
		RULE_fixedArray = 18, RULE_identifierArray = 19;
	public static final String[] ruleNames = {
		"prog", "include", "clazz", "bitfield", "bits", "struct", "field", "type", 
		"defaultValue", "floatDefaultValue", "numberDefaultValue", "decimalValue", 
		"stringDefaultValue", "basicType", "stringType", "structType", "arrayOfStruct", 
		"varlenArray", "fixedArray", "identifierArray"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'import'", "';'", "'bitfield'", "'{'", "'}'", "':'", "'struct'", 
		"'='", "'+'", "'-'", "'byte'", "'int8'", "'uint8'", "'short'", "'int16'", 
		"'uint16'", "'int'", "'int32'", "'uint32'", "'long'", "'int64'", "'uint64'", 
		"'float'", "'double'", "'string'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "Identifier", "DecimalLiteral", "HexLiteral", 
		"StringLiteral", "FloatingPointLiteral", "WS", "IGNORED_COMMENT", "SL_COMMENT", 
		"COMMENT"
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
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(40);
				include();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				clazz();
				}
				}
				setState(49); 
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
			setState(51);
			match(T__0);
			setState(52);
			match(StringLiteral);
			setState(53);
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
			setState(57);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				struct();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
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
			setState(59);
			match(T__2);
			setState(60);
			match(Identifier);
			setState(61);
			match(T__3);
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				bits();
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Identifier );
			setState(67);
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
			setState(69);
			match(Identifier);
			setState(70);
			match(T__5);
			setState(71);
			match(DecimalLiteral);
			setState(73);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(72);
				numberDefaultValue();
				}
			}

			setState(75);
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
			setState(77);
			match(T__6);
			setState(78);
			match(Identifier);
			setState(79);
			match(T__3);
			setState(81); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(80);
				field();
				}
				}
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << Identifier))) != 0) );
			setState(85);
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
			setState(87);
			type();
			setState(88);
			match(Identifier);
			setState(90);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(89);
				defaultValue();
				}
			}

			setState(92);
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
		public ArrayOfStructContext arrayOfStruct() {
			return getRuleContext(ArrayOfStructContext.class,0);
		}
		public ClazzContext clazz() {
			return getRuleContext(ClazzContext.class,0);
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
			setState(109);
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
				setState(94);
				basicType();
				setState(96);
				_la = _input.LA(1);
				if (_la==T__25) {
					{
					setState(95);
					fixedArray();
					}
				}

				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				stringType();
				setState(99);
				fixedArray();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				structType();
				setState(103);
				_la = _input.LA(1);
				if (_la==T__25) {
					{
					setState(102);
					arrayOfStruct();
					}
				}

				}
				break;
			case T__2:
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				clazz();
				setState(107);
				_la = _input.LA(1);
				if (_la==T__25) {
					{
					setState(106);
					arrayOfStruct();
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
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				numberDefaultValue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				stringDefaultValue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
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
			setState(116);
			match(T__7);
			setState(117);
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
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				match(T__7);
				setState(120);
				match(HexLiteral);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				match(T__7);
				setState(122);
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
			setState(126);
			_la = _input.LA(1);
			if (_la==T__8 || _la==T__9) {
				{
				setState(125);
				_la = _input.LA(1);
				if ( !(_la==T__8 || _la==T__9) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(128);
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
			setState(130);
			match(T__7);
			setState(131);
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
			setState(133);
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
			setState(135);
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
			setState(137);
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

	public static class ArrayOfStructContext extends ParserRuleContext {
		public VarlenArrayContext varlenArray() {
			return getRuleContext(VarlenArrayContext.class,0);
		}
		public FixedArrayContext fixedArray() {
			return getRuleContext(FixedArrayContext.class,0);
		}
		public IdentifierArrayContext identifierArray() {
			return getRuleContext(IdentifierArrayContext.class,0);
		}
		public ArrayOfStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayOfStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).enterArrayOfStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StructListener ) ((StructListener)listener).exitArrayOfStruct(this);
		}
	}

	public final ArrayOfStructContext arrayOfStruct() throws RecognitionException {
		ArrayOfStructContext _localctx = new ArrayOfStructContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arrayOfStruct);
		try {
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				varlenArray();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				fixedArray();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
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
		enterRule(_localctx, 34, RULE_varlenArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(T__25);
			setState(145);
			match(T__26);
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
		enterRule(_localctx, 36, RULE_fixedArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(T__25);
			setState(148);
			match(DecimalLiteral);
			setState(149);
			match(T__26);
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
		enterRule(_localctx, 38, RULE_identifierArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__25);
			setState(152);
			match(Identifier);
			setState(153);
			match(T__26);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u009e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\2\6\2\62"+
		"\n\2\r\2\16\2\63\3\3\3\3\3\3\3\3\3\4\3\4\5\4<\n\4\3\5\3\5\3\5\3\5\6\5"+
		"B\n\5\r\5\16\5C\3\5\3\5\3\6\3\6\3\6\3\6\5\6L\n\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\6\7T\n\7\r\7\16\7U\3\7\3\7\3\b\3\b\3\b\5\b]\n\b\3\b\3\b\3\t\3\t\5\t"+
		"c\n\t\3\t\3\t\3\t\3\t\3\t\5\tj\n\t\3\t\3\t\5\tn\n\t\5\tp\n\t\3\n\3\n\3"+
		"\n\5\nu\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\5\f~\n\f\3\r\5\r\u0081\n\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\5"+
		"\22\u0091\n\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\2\2\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\4\3\2\13\f"+
		"\3\2\r\32\u009c\2-\3\2\2\2\4\65\3\2\2\2\6;\3\2\2\2\b=\3\2\2\2\nG\3\2\2"+
		"\2\fO\3\2\2\2\16Y\3\2\2\2\20o\3\2\2\2\22t\3\2\2\2\24v\3\2\2\2\26}\3\2"+
		"\2\2\30\u0080\3\2\2\2\32\u0084\3\2\2\2\34\u0087\3\2\2\2\36\u0089\3\2\2"+
		"\2 \u008b\3\2\2\2\"\u0090\3\2\2\2$\u0092\3\2\2\2&\u0095\3\2\2\2(\u0099"+
		"\3\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\61\3\2\2\2"+
		"/-\3\2\2\2\60\62\5\6\4\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63"+
		"\64\3\2\2\2\64\3\3\2\2\2\65\66\7\3\2\2\66\67\7!\2\2\678\7\4\2\28\5\3\2"+
		"\2\29<\5\f\7\2:<\5\b\5\2;9\3\2\2\2;:\3\2\2\2<\7\3\2\2\2=>\7\5\2\2>?\7"+
		"\36\2\2?A\7\6\2\2@B\5\n\6\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2DE"+
		"\3\2\2\2EF\7\7\2\2F\t\3\2\2\2GH\7\36\2\2HI\7\b\2\2IK\7\37\2\2JL\5\26\f"+
		"\2KJ\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7\4\2\2N\13\3\2\2\2OP\7\t\2\2PQ\7\36"+
		"\2\2QS\7\6\2\2RT\5\16\b\2SR\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3"+
		"\2\2\2WX\7\7\2\2X\r\3\2\2\2YZ\5\20\t\2Z\\\7\36\2\2[]\5\22\n\2\\[\3\2\2"+
		"\2\\]\3\2\2\2]^\3\2\2\2^_\7\4\2\2_\17\3\2\2\2`b\5\34\17\2ac\5&\24\2ba"+
		"\3\2\2\2bc\3\2\2\2cp\3\2\2\2de\5\36\20\2ef\5&\24\2fp\3\2\2\2gi\5 \21\2"+
		"hj\5\"\22\2ih\3\2\2\2ij\3\2\2\2jp\3\2\2\2km\5\6\4\2ln\5\"\22\2ml\3\2\2"+
		"\2mn\3\2\2\2np\3\2\2\2o`\3\2\2\2od\3\2\2\2og\3\2\2\2ok\3\2\2\2p\21\3\2"+
		"\2\2qu\5\26\f\2ru\5\32\16\2su\5\24\13\2tq\3\2\2\2tr\3\2\2\2ts\3\2\2\2"+
		"u\23\3\2\2\2vw\7\n\2\2wx\7\"\2\2x\25\3\2\2\2yz\7\n\2\2z~\7 \2\2{|\7\n"+
		"\2\2|~\5\30\r\2}y\3\2\2\2}{\3\2\2\2~\27\3\2\2\2\177\u0081\t\2\2\2\u0080"+
		"\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7\37"+
		"\2\2\u0083\31\3\2\2\2\u0084\u0085\7\n\2\2\u0085\u0086\7!\2\2\u0086\33"+
		"\3\2\2\2\u0087\u0088\t\3\2\2\u0088\35\3\2\2\2\u0089\u008a\7\33\2\2\u008a"+
		"\37\3\2\2\2\u008b\u008c\7\36\2\2\u008c!\3\2\2\2\u008d\u0091\5$\23\2\u008e"+
		"\u0091\5&\24\2\u008f\u0091\5(\25\2\u0090\u008d\3\2\2\2\u0090\u008e\3\2"+
		"\2\2\u0090\u008f\3\2\2\2\u0091#\3\2\2\2\u0092\u0093\7\34\2\2\u0093\u0094"+
		"\7\35\2\2\u0094%\3\2\2\2\u0095\u0096\7\34\2\2\u0096\u0097\7\37\2\2\u0097"+
		"\u0098\7\35\2\2\u0098\'\3\2\2\2\u0099\u009a\7\34\2\2\u009a\u009b\7\36"+
		"\2\2\u009b\u009c\7\35\2\2\u009c)\3\2\2\2\21-\63;CKU\\bimot}\u0080\u0090";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}