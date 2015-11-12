[
 :module "TemplateEngine"
 :project "org.soulspace.template"
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :version "1.0.3"
 :type :component
 :description "General purpose template engine"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global"
           ["org.soulspace.baumeister/JavaPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["oro/oro, 2.0.8"]
                ["junit/junit, 3.8.1" :dev]
                ["com.clarkware/junitperf, 1.9.1" :dev]
                ["org.fitnesse/fitnesse, 20060719" :dev]
                ["org.fitnesse/fitlibrary, 20060719" :dev]]
 ]
