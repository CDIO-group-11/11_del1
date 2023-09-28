@echo off
javac -sourcepath ./src/ -d ./bin/ ./src/App/spil/Main.java
cd bin
java App/spil/Main
pause