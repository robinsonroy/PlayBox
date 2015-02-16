set javaPath=[the path of the directory containing javac.exe]
for %%* in (.) do (set programName=%%~n*)

%javaPath%\javac.exe -classpath .;jl1.0.1.jar *.java
%javaPath%\java.exe -classpath .;jl1.0.1.jar %programName%

pause