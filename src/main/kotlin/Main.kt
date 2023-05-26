import java.lang.Exception
import java.util.Scanner

fun main(args: Array<String>) {
    var menuArchiveSize : Int
    val listOfArchive : MutableList<Data> = mutableListOf()

    while(true){
        menuArchiveSize= createMenu("Создать новый архив", "Выход", listOfArchive)
        val action=Scanner(System.`in`).nextLine()
        if( checkInput(action, listOfArchive)){
            determineActionArchive(action.toInt(), menuArchiveSize, listOfArchive)
        }
    }
}