import java.lang.Exception
import java.util.Scanner
import kotlin.system.exitProcess

fun checkInput(action : String, listOfSomething: MutableList<Data>) : Boolean{   // Проверка корректности ввода пользователя
    try{
        action.toInt()
    }
    catch (e: Exception) {
        println("Введите пожалуйста цифру, а не символ")
        return false
    }
    if((action.toInt() > (listOfSomething.size + 1))){
        println("Нет такого пункта меню, введите пожалуйста цифру из меню")
        return false
    }
    return true
}

// Создание пунктов меню Архивов/Заметок
fun createMenu(itemFirst : String, itemLast : String, listOfSomething: MutableList<Data> ): Int{
    val menuItem: MutableList<String> = mutableListOf(itemFirst)
    listOfSomething.forEach{element ->
        menuItem.add("Открыть ${element.name}")
    }
    menuItem.add(itemLast)
    for ((i, element) in menuItem.withIndex()){
        println("$i $element")
    }
    return menuItem.size
}

// Обработка корректного ввода пользователя меню Архивов
fun determineActionArchive(numberAction: Int, menuArchiveSize : Int, listOfArchive: MutableList<Data>) {
    when(numberAction){
        0 -> createArchive(listOfArchive)
        in 1..menuArchiveSize-2 -> {
            println("Меню для архива: ${listOfArchive[numberAction-1].name}")
            while (true){
                 val currentListOfNotes = (listOfArchive[numberAction-1] as Archive).listOfNotes
                 val menuNoteSize=createMenu("Создать новую заметку", "Назад", currentListOfNotes)
                 val action= Scanner(System.`in`).nextLine()
                 if (checkInput(action, currentListOfNotes)){
                     val back: Boolean=determineActionNote(action.toInt(), menuNoteSize, currentListOfNotes )
                     if (back) break
                }
            }
        }
        menuArchiveSize-1 -> exitProcess(0)
    }
}

// Обработка корректного ввода пользователя меню Заметок
fun determineActionNote(numberAction : Int, menuNotesSize : Int, listOfNotes : MutableList<Data>): Boolean {
    var back=false
    when (numberAction) {
        0 -> createNote(listOfNotes)
        in 1..menuNotesSize - 2 -> { println("Текст выбранной заметки: ${(listOfNotes[numberAction - 1] as Note).text}")}
        menuNotesSize - 1 -> back=true
    }
    return back
}

fun createArchive(listOfArchive: MutableList<Data>) {
    println("введите имя Архива:")
    val nameArchive: String = Scanner(System.`in`).nextLine()
    listOfArchive.add(Archive(nameArchive))
    println("Архив $nameArchive создан")
}

fun createNote(listOfNotes : MutableList<Data>) {
    println("введите имя Заметки:")
    val nameNote: String = Scanner(System.`in`).nextLine()
    println("введите текст Заметки:")
    val text: String = Scanner(System.`in`).nextLine()
    listOfNotes.add(Note(nameNote,text))
    println("Заметка [$nameNote] с текстом: [$text] создана")
}