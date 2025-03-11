function getVolume(){
    const figure = document.getElementById('figure').value;
    const edges = document.getElementById('edge').value;
    const accuracy = document.getElementById('accuracy').value;

    // Проверка формата данных
    if (!figure || !edges || !accuracy) {
        alert("Заполните все поля!");
        return;
    }

    // Проверка, что edges — это числа, разделенные запятыми
    const edgesArray = edges.split(',');
    if (!edgesArray.every(edge => !isNaN(parseFloat(edge)))) {
        alert("Длины граней должны быть числами, разделенными запятыми!");
        return;
    }

    // Проверка, что accuracy — это число
    if (isNaN(parseFloat(accuracy))) {
        alert("Точность должна быть числом!");
        return;
    }

    // Отправка данных на сервер
    fetch('/volume', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            figure: figure,
            edges: edgesArray.map(Number),
            accuracy: parseFloat(accuracy)
        }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.error) {
                alert(data.error);
            } else {
                alert(`Объем ${figure}: ${data.volume}`);
            }
        })
        .catch(error => {
            console.error('Ошибка:', error);
            alert('Произошла ошибка при расчете объема.');
        });
}