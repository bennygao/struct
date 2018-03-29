CC=g++
RM=rm
ECHO=echo

OBJS=democpp.o main.o structpp.o
CFLAGS=-std=c++11 -O3 -Wall -I. -c
LDFLAGS=$(ARM_LIB_DIR) -lpthread
TARGET=demo

all: $(TARGET)

.cpp.o:
	$(CC) $(CFLAGS) -c $<

$(TARGET): $(OBJS)
	$(CC) -o $(TARGET) $(OBJS) $(LDFLAGS)

clean:
	$(RM) -f $(OBJS) $(TARGET)

