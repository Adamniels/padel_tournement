# Variabler
SRC_DIR = src
OUT_DIR = classes

# Standardmål: Kompilera alla Java-filer
all:
	find $(SRC_DIR) -name "*.java" | xargs javac -d $(OUT_DIR) -sourcepath $(SRC_DIR)

# Kör huvudprogrammet
run: all
	java -cp $(OUT_DIR) src.Main

# Rensa kompilerade filer
clean:
	rm -rf $(OUT_DIR)

# Hjälp
help:
	@echo "Kommandon:"
	@echo "  make        - Kompilerar alla Java-filer"
	@echo "  make run    - Kompilerar och kör programmet"
	@echo "  make clean  - Tar bort alla kompilerade filer"