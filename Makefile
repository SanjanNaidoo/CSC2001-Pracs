# Variables
JAVAC = /usr/bin/javac
JAVA = java
JAVADOC = javadoc
SRCDIR = src
BINDIR = bin
DOC = doc
MAIN_ARRAY = GenericsKbArrayApp
MAIN_BST = GenericsKbBSTApp

# Classes to Compile
CLASSES = GenericsKbArrayApp.class GenericsKbBSTApp.class
CLASS_FILES = $(CLASSES:%.class=$(BINDIR)/%.class)

# Suffix Rule for Java Compilation
.SUFFIXES: .java .class
$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

# Default target to build all classes
default: $(CLASS_FILES)

# Generate Javadoc
doc:
	mkdir -p $(DOC)
	$(JAVADOC) -d $(DOC) $(SRCDIR)/*.java

# Clean build and docs
clean:
	rm -rf $(BINDIR)/*.class $(DOC)/*

# Run Array-based App
run-array: $(BINDIR)/$(MAIN_ARRAY).class
	$(JAVA) -cp $(BINDIR) $(MAIN_ARRAY)

# Run the BST App
run-bst: $(BINDIR)/$(MAIN_BST).class
	$(JAVA) -cp $(BINDIR) $(MAIN_BST)


