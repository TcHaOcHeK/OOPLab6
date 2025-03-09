async function getResp(){
    try {
        // Отправляем GET-запрос к сервлету
        const response = await fetch("http://localhost:8080/hello");

        // Получаем текстовый ответ
        const data = await response.text();

        // Выводим ответ на страницу
        document.getElementById("result").innerText = data;
    } catch (error) {
        console.error("Ошибка при отправке запроса:", error);
    }
}