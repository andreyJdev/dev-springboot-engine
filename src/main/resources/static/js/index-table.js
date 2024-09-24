$(document).ready(function () {
    $('#nav-price').on('click', function (event) {
        event.preventDefault();

        $('.content-index').toggle(); // Скрыть/показать содержимое div с классом main
        $('#table-container').empty(); // Очистить ранее выведенную информацию

        if ($('.content-index').is(':hidden')) {
            $.ajax({
                url: '/api/products',
                method: 'GET',
                success: function (response) {
                    console.log('Данные получены:', response); // Отладка

                    // Вычисляем текущий год минус 5 месяцев
                    var currentDate = new Date();
                    currentDate.setMonth(currentDate.getMonth() - 5);
                    var year = currentDate.getFullYear();

                    // Создаем контейнеры для заголовка и таблиц
                    var headerContainer = $('<div></div>').addClass('container text-margin');
                    var tablesContainer = $('<div></div>').addClass('container mob-table');
                    var tableResponsive = $('<div></div>').addClass('table-responsive');

                    headerContainer.append('<h1>Каталог саженцев, черенков винограда на осень ' + year + ' - весна ' + (year + 1) + ' года</h1>');

                    function generateTable(data) {
                        var table = $('<table></table>').addClass('table table-hover table-striped');
                        var thead = $('<thead></thead>');
                        var tbody = $('<tbody></tbody>');

                        // Заголовки таблицы на русском языке
                        var headers = [
                            "Название сорта",
                            "Срок созревания",
                            "Сила роста",
                            "Гроздь",
                            "Ягода",
                            "Вкус и консистенция мякоти",
                            "Морозо-стойкость",
                            "Цена (Руб) саженца",
                            "Цена (Руб) черенка"
                        ];
                        var headerRow = $('<tr></tr>');
                        headers.forEach(function (header) {
                            headerRow.append($('<th scope="col"></th>').text(header));
                        });
                        thead.append(headerRow);
                        table.append(thead);

                        // Данные таблицы
                        data.forEach(function (item) {
                            var row = $('<tr></tr>');
                            row.append($('<th scope="row"></th>').text(item.name + (item.selectionMini ? " (" + item.selectionMini + ")" : "")));
                            row.append($('<td></td>').text(item.time));
                            row.append($('<td></td>').text(item.strength));
                            row.append($('<td></td>').text(item.cluster));
                            row.append($('<td></td>').text(item.berry));
                            row.append($('<td></td>').text(item.taste));
                            row.append($('<td></td>').text(item.resistanceCold));
                            row.append($('<td></td>').text(item.priceSeed));
                            row.append($('<td></td>').text(item.priceCut));
                            tbody.append(row);
                        });
                        table.append(tbody);

                        return table;
                    }

                    // Обрабатываем каждый набор данных
                    if (response.hasOwnProperty("-")) {
                        tableResponsive.append(generateTable(response["-"]));
                    }

                    for (var key in response) {
                        if (response.hasOwnProperty(key) && key !== "-") {
                            var rowDiv = $('<div></div>').addClass('row');
                            var colDiv = $('<div></div>').addClass('col');
                            colDiv.append('<p style="margin-top: 20px;" class="text-center fw-600">' + key + '</p>');
                            rowDiv.append(colDiv);
                            tableResponsive.append(rowDiv);
                            tableResponsive.append(generateTable(response[key]));
                        }
                    }

                    // Добавляем заголовок и таблицы в table-container
                    tablesContainer.append(tableResponsive);
                    tablesContainer.append('<p class="text-margin fw-600">* — Без подтверждения сортности.</p>');
                    $('#table-container').append(headerContainer);
                    $('#table-container').append(tablesContainer);

                    // Показываем контейнер с таблицами
                    $('#table-container').show();
                },
                error: function (error) {
                    console.log('Ошибка при получении данных:', error);
                }
            });
        } else {
            // Скрываем контейнер с таблицами, если main снова показывается
            $('#table-container').hide();
        }
    });
});