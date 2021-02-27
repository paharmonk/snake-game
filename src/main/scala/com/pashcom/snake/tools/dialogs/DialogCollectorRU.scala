package com.pashcom.snake.tools.dialogs

import com.pashcom.snake.tools.UserDataCollector

object DialogCollectorRU extends DialogCollector {

  def dialogStartGame(): Unit = {
    var res = CustomDialog.dialog(s"Привет ${UserDataCollector.getUserName}. Сыграем в игру?",
      Array("Давай", "Ты чё больной?"))

    if (res == 1) {
      CustomDialog.dialog("Я соовершенно здоров, а вот тебе скоро может потребоваться доктор.",
        Array("Ты кто вообще такой?"))

      CustomDialog.dialog("Я Конструктор. А это моя игра. Предлагаю тебе в неё сыграть",
        Array("Полагаю, ты просто так от меня не отстанешь..."))
    }

    CustomDialog.dialog(s"Я тут посмотрел, у тебя на компьютере много всяких интересных файлов.",
      Array("Да ладно"))

    CustomDialog.dialog(s"Так вот, среди них" +
      s"\n\n${UserDataCollector.findUserFiles.mkString("\n")}",
      Array("Погоди, ты что в мой компьютер пробрался??? Ты вирус?"))

    res = CustomDialog.dialog(s"Нет, я не вирус. Но твои файлы могу так же удалить." +
      s"\n\nЧто тут у нас еще есть. Полно всего, в экран не влезет всё показывать:" +
      s"\n${UserDataCollector.getUserDrives.mkString("\n")}" +
      s"\n\nТак вот, если проиграешь, я что-то где-то удалю. Или много где удалю. Ха-ха.",
      Array("Мне пофиг на эти файлы. Сам хотел удалить.", "Ээ, ты реально больной?"))

    var extraText = if (res == 0) "И еще кое-что перед началом, если тебе вдруг станет не пофиг."
                    else "И еще кое-что перед началом."

    res = CustomDialog.dialog(s"$extraText\n\nНебось считаешь себя мего умным, думаешь, что сейчас закроешь" +
      s"\nмою игру и всё? Обрадую тебя. Ничего не выйдет." +
      s"\nМожет быть я переопределил поведение кнопки 'крестик'," +
      s"\nа может повесил дополнительный хук на завершение процесса." +
      s"\n\nВ общем, не советую тебе закрывать окно, а то ТВОИ ФАЙЛЫ УДАЛЯТСЯ.",
      Array("Ты реально злой гений. Уважаю.", "Я пожалуюсь своей бабушке"))

    extraText = if (res == 1) "Вот молодец, хоть проведуешь её. Когда последний раз навещал?\n\n"
                else ""

    CustomDialog.dialog(s"${extraText}Ладно. Стартуем.",
      Array("Стартуем", "Стартуем"))
  }

  def dialogGameOver(): Unit = {
    val res = CustomDialog.dialog("Начать сначала?",
      Array("Ок", "Выход"))

    val extraText = if (res == 0) "Азазаза. Обманул. Нельзя сначала начинать.\n\n"
                    else ""

    CustomDialog.dialog(s"${extraText}Как и предупреждал, я пошел удалять твои файлы. Чао.",
      Array("Ок"))
  }

  def dialogGameWin(): Unit = {
    CustomDialog.dialog(s"${UserDataCollector.getUserName}," +
      s"\nне знаю как тебе это удалось, но ты победил." +
      s"\nНа данный момент все прибывшие Лунтики успешно уничтожены." +
      s"\nПриходи завтра за новыми." +
      s"\n\nПоздравляю. Ты крутой. Можешь всем теперь похвастаться.",
      Array("Выход"))
  }
}
