pushd "D:\git\itol-redmine\itol\itol-redmine"
set CP=D:\git\itol-redmine\itol\itol-redmine\bin
set CP=%CP%;D:\git\itol-redmine\itol\itol-db\bin
set CP=%CP%;D:\git\itol-redmine\itol\itol-addin\bin
set CP=%CP%;D:\git\itol-redmine\itol\itol-addin\lib\joa.jar
set CP=%CP%;C:\Users\Wolfgang\.gradle\caches\modules-2\files-2.1\junit\junit\4.12\2973d150c0dc1fefe998f834810d68f278ea58ec\junit-4.12.jar
set CP=%CP%;C:\Users\Wolfgang\.gradle\caches\modules-2\files-2.1\org.hamcrest\hamcrest-core\1.3\42a25dc3219429f0e5d060061f71acb49bf010a0\hamcrest-core-1.3.jar
"C:\Program Files\Java\jre1.8.0_71\bin\java.exe" -classpath "%CP%" com.wilutions.redmineaddin.IssueApplication
