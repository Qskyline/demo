set source_path=C:\Users\skyline\Desktop\code\node\demo\dist
set run_path=C:\Users\skyline\Desktop\code\eclipse\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\demo
set code_path=C:\Users\skyline\Desktop\code\eclipse\demo\src\main\webapp

rd %run_path%\resource /s /q
rd %code_path%\resource /s /q
del %run_path%\WEB-INF\views\index.jsp
del %code_path%\WEB-INF\views\index.jsp

xcopy %source_path%\resource %run_path%\resource /E /F /I /d
xcopy %source_path%\resource %code_path%\resource /E /F /I /d

type %source_path%\index.html >> %run_path%\WEB-INF\views\index.jsp
echo f | xcopy %run_path%\WEB-INF\views\index.jsp %code_path%\WEB-INF\views\index.jsp /F /d

