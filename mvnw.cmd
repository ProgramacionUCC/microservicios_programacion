@echo off
setlocal
set "MAVEN_VERSION=3.9.10"
set "MAVEN_HOME=%~dp0.tools\apache-maven-%MAVEN_VERSION%"
if not defined JAVA_HOME if exist "%ProgramFiles%\Eclipse Adoptium" (
  for /d %%D in ("%ProgramFiles%\Eclipse Adoptium\jdk-*") do if not defined JAVA_HOME set "JAVA_HOME=%%~fD"
)
if not defined JAVA_HOME if exist "%ProgramFiles%\Java" (
  for /d %%D in ("%ProgramFiles%\Java\jdk-*") do if not defined JAVA_HOME set "JAVA_HOME=%%~fD"
)
if not defined JAVA_HOME (
  echo JAVA_HOME no esta definido y no se encontro un JDK instalado.
  echo Configuralo con: setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot"
  exit /b 1
)
if not exist "%MAVEN_HOME%\bin\mvn.cmd" (
  echo Maven local no encontrado. Ejecuta la instalacion indicada en README.md.
  exit /b 1
)
call "%MAVEN_HOME%\bin\mvn.cmd" %*
