# Variabler
SRC_DIR = src
OUT_DIR = classes
LIBS_DIR = libs
JAR_FILES = $(LIBS_DIR)/gson-2.12.1.jar

# Standardmål: Kompilera alla Java-filer
all:
	mkdir -p $(OUT_DIR)
	find $(SRC_DIR) -name "*.java" | xargs javac -cp $(JAR_FILES) -d $(OUT_DIR) -sourcepath $(SRC_DIR)

# Kör huvudprogrammet
run: all
	java -cp $(OUT_DIR):$(JAR_FILES) Main

# Rensa kompilerade filer
clean:
	rm -rf $(OUT_DIR)

# Hjälp
help:
	@echo "Kommandon:"
	@echo "  make        - Kompilerar alla Java-filer"
	@echo "  make run    - Kompilerar och kör programmet"
	@echo "  make clean  - Tar bort alla kompilerade filer"
