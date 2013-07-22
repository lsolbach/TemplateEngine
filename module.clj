[
 :module "TemplateEngineTask"
 :project "org.soulspace.template"
 :project-lead "Ludger Solbach"
 :vendor "soulspace.org"
 :type "framework"
 :version "0.1.0"
 :description "Ant task for the use of the template engine in ant build files."
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global" "sdeps" "java" "package"]
 :dependencies [[["org.soulspace.template" "TemplateEngine" "1.0.0"]]
                [["org.soulspace.common" "JavaUtilLibrary" "0.3.0"]]
                [["oro" "oro" "2.0.8"]]
                [["org.apache.ant" "ant" "1.8.3"] "dev"]
                [["junit" "junit" "3.8.1"] "dev"]]
 ]
