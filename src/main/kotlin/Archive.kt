class Archive(archiveName:String, val listOfNotes : MutableList<Data> = mutableListOf()) : Data() {
    override val name=archiveName
}