# Variables
JAVAC = javac
JAVA = java
SRC = src
BIN = bin
DOC = doc
MAIN_ARRAY = GenericsKbArrayApp
MAIN_BST = GenericsKbBSTApp

# Default target
all: compile doc

# Compile all Java source files
compile:
	$(JAVAC) -d $(BIN) $(SRC)/*.java

# Generate Javadoc
doc:
	$(JAVADOC) -d $(DOC) $(SRC)/*.java

# Run Array-based App
run-array:
	$(JAVA) -cp $(BIN) $(MAIN_ARRAY)

# Clean build and docs
clean:
	rm -rf $(BIN)/* $(DOC)/*

# Run the BST app
run-bst:
	java -cp bin GenericsKbBSTApp

