import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "com.sample;"
typeMapping = [
        (~/(?i)int/)                      : "long",
        (~/(?i)float|double|decimal|real/): "double",
        (~/(?i)datetime|timestamp/)       : "java.sql.Timestamp",
        (~/(?i)date/)                     : "java.sql.Date",
        (~/(?i)time/)                     : "java.sql.Time",
        (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {

    println table
//generateFetch(table.getName())


//
//  def className = javaName(table.getName(), true)
//  def fields = calcFields(table)
//  new File(dir, className + ".java").withPrintWriter { out -> generate(out, className, fields) }
}



def generateFetch(tableName){

}

//def generate(out, className, fields) {

//}
//
def calcFields(table) {
  DasUtil.getColumns(table).reduce([]) { fields, col ->
    def spec = Case.LOWER.apply(col.getDataType().getSpecification())
    fields += [[
                 name : javaName(col.getName(), false),
                 type : spec,
                 annos: ""]]
  }
}

def procedureName(){

}