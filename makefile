IN=*.java
CC=javac
CP=-cp .:lib/*
target:
	$(CC) $(CP) $(IN)
jar:
	jar cvf Main.jar *
	jar cvmf manifest.mf Main.jar Main.class

