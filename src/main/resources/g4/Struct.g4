grammar Struct;

prog
    : include* clazz+
    ;

include
    : 'import' StringLiteral ';'
    ;

clazz
    : struct
    | bitfield
    ;

bitfield
    : 'bitfield' Identifier '{' bits+ '}'
    ;

bits
    : Identifier ':' DecimalLiteral numberDefaultValue? ';'
    ;

struct
    : 'struct' Identifier '{' field+ '}'
    ;

field
    : type Identifier defaultValue? ';'
    ;

type
    : basicType fixedArray?
    | stringType fixedArray
    | structType arrayOfStruct?
    | clazz arrayOfStruct?
    ;

defaultValue
    : numberDefaultValue
    | stringDefaultValue
    | floatDefaultValue
    ;

floatDefaultValue
    : '=' FloatingPointLiteral
    ;

numberDefaultValue
    : '=' HexLiteral
    | '=' decimalValue
    ;

decimalValue
    : ('+'|'-')? DecimalLiteral
    ;

stringDefaultValue
    : '=' StringLiteral
    ;

basicType
    : 'byte'
    | 'int8'
    | 'uint8'
    | 'short'
    | 'int16'
    | 'uint16'
    | 'int'
    | 'int32'
    | 'uint32'
    | 'long'
    | 'int64'
    | 'uint64'
    | 'float'
    | 'double'
    ;

stringType
    : 'string'
    ;

structType
    : Identifier
    ;

arrayOfStruct
    : varlenArray
    | fixedArray
    | identifierArray
    ;

varlenArray
    : '[' ']'
    ;

fixedArray
    : '[' DecimalLiteral ']'
    ;

identifierArray
    : '[' Identifier ']'
    ;

Identifier
    : Letter (Letter|JavaIDDigit)*
    ;

// LEXER
DecimalLiteral
    : ('0' | '1'..'9' '0'..'9'*)
    ;

HexLiteral
    : ('0x' | '0X') HexDigit+
    ;

StringLiteral
    : '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

FloatingPointLiteral
    : ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    | '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    | ('0'..'9')+ Exponent FloatTypeSuffix?
    | ('0'..'9')+ FloatTypeSuffix
    | ('0x' | '0X') (HexDigit )*
      ('.' (HexDigit)*)?
      ( 'p' | 'P' )
      ( '+' | '-' )?
      ( '0' .. '9' )+
      FloatTypeSuffix?
    ;

fragment
HexDigit
    : ('0'..'9'|'a'..'f'|'A'..'F')
    ;

fragment
Exponent
    : ('e'|'E') ('+'|'-')? ('0'..'9')+
    ;

fragment
FloatTypeSuffix
    : ('f'|'F'|'d'|'D')
    ;

fragment
EscapeSequence
    : '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    | OctalEscape
    ;

fragment
OctalEscape
    : '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    | '\\' ('0'..'7') ('0'..'7')
    | '\\' ('0'..'7')
    ;

fragment
Letter
    : '\u0024'
    | '\u0041'..'\u005a'
    | '\u005f'
    | '\u0061'..'\u007a'
    | '\u00c0'..'\u00d6'
    | '\u00d8'..'\u00f6'
    | '\u00f8'..'\u00ff'
    | '\u0100'..'\u1fff'
    | '\u3040'..'\u318f'
    | '\u3300'..'\u337f'
    | '\u3400'..'\u3d2d'
    | '\u4e00'..'\u9fff'
    | '\uf900'..'\ufaff'
    ;

fragment
JavaIDDigit
    : '\u0030'..'\u0039'
    | '\u0660'..'\u0669'
    | '\u06f0'..'\u06f9'
    | '\u0966'..'\u096f'
    | '\u09e6'..'\u09ef'
    | '\u0a66'..'\u0a6f'
    | '\u0ae6'..'\u0aef'
    | '\u0b66'..'\u0b6f'
    | '\u0be7'..'\u0bef'
    | '\u0c66'..'\u0c6f'
    | '\u0ce6'..'\u0cef'
    | '\u0d66'..'\u0d6f'
    | '\u0e50'..'\u0e59'
    | '\u0ed0'..'\u0ed9'
    | '\u1040'..'\u1049'
    ;

WS
    : [ \t\n\r]+ -> skip
    ;

IGNORED_COMMENT
    : '#' .*? '\n' -> skip
    ;

SL_COMMENT
    : '//' .*? '\n' -> channel(HIDDEN)
    ;

COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN)
    ;
