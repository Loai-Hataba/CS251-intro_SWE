@echo off
cd /d "%~dp0"
echo Current directory is: %cd%
java -cp target/classes com.budgetapp.Main
pause