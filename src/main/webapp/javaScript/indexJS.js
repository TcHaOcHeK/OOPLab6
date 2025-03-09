async function getResp(){
    try {
        // Отправляем GET-запрос к сервлету
        const response = await fetch("http://localhost:8080/hello");

        // Получаем текстовый ответ
        // Выводим ответ на страницу
        document.getElementById("result").innerText = await response.text();
    } catch (error) {
        console.error("Ошибка при отправке запроса:", error);
    }
}