function trimPunctuation(str) {
    return str.replace(/^[\p{P}\p{S}]+|[\p{P}\p{S}]+$/gu, '');
}

$(document).ready(function () {
    $('.images_search_form').on('click', function (event) {
        event.preventDefault(); // Предотвращаем переход по ссылке

        fetch('/api/images')
            .then(response => response.json())
            .then(data => {
                var $gallery = $('.card-product-all-images');
                $gallery.empty(); // Очищаем галерею перед добавлением новых изображений

                data.forEach(function (imageName) {
                    var $img = $('<img>', {
                        src: '/images/' + imageName,  // Путь к изображениям в static/images
                        class: 'card-search-img',  // Стили миниатюр, скругленные края и адаптивность
                        alt: trimPunctuation(imageName).trim(), // Ограничение высоты, обрезка изображения и Flexbox стили
                    });
                    $gallery.append($img);
                });

                // Плавное появление карточки
                $('.overlay-card-product').fadeIn(200);
            })
            .catch(error => console.error('Error fetching images:', error));
    });

    // Обработчик для закрытия карточки (скрываем, но не удаляем)
    $('.close-btn').on('click', function () {
        $(this).closest('.overlay-card-product').fadeOut(200);
    });

    // Обработчик для закрытия карточки по клику на сам элемент
    $('.overlay-card-product').on('click', function (event) {
        if (!$(event.target).closest('.card-product').length) {
            $(this).fadeOut(200);
        }
    });

    // Обработчик для клика по изображению
    $(document).on('click', '.card-search-img', function () {
        var imageName = $(this).attr('alt');
        if (imageName !== "Upload Image") {
            $('#image').val(imageName);  // Устанавливаем название изображения в input
            $('.overlay-card-product').fadeOut(200);  // Закрываем форму
        }
    });


    function uploadImage() {
        var formData = new FormData();
        var file = $("#image-upload")[0].files[0];

        if (file) {
            formData.append("image", file);

            $.ajax({
                url: '/api/images/upload',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    // Помещаем имя файла в поле input с id="image"
                    $('#image').val(file.name);

                    // Очищаем input для загрузки файла
                    $('#image-upload').val('');

                    // Показываем сообщение об успешной загрузке
                    $("#message").text("Файл '" + file.name + "' успешно загружен.");

                    // Опционально: скрываем сообщение через некоторое время
                    setTimeout(function () {
                        $("#message").text("");
                    }, 3000);  // скрываем сообщение через 3 секунды

                    $('.overlay-card-product').fadeOut(200);

                    // Опционально: Обновляем галерею изображений
                    // reloadImages();
                },
                error: function (response) {
                    $("#message").text("Ошибка при загрузке изображения");
                }
            });
        } else {
            $("#message").text("Пожалуйста, выберите файл для загрузки");
        }
    }

    // Клик по дефолтному изображению через $(document).on для динамических элементов
    $(document).on('click', '#default-upload', function () {
        $('#image-upload').trigger('click');  // Открываем диалог выбора файла
    });

    // Событие на выбор файла
    $('#image-upload').on('change', function () {
        uploadImage();  // Загружаем файл после выбора
    });

    // Остальные обработчики для изображений, карточек и т.д.
    $('.images_search_form').on('click', function (event) {
        event.preventDefault(); // Предотвращаем переход по ссылке

        fetch('/api/images')
            .then(response => response.json())
            .then(data => {
                var $gallery = $('.card-product-all-images');
                $gallery.empty(); // Очищаем галерею перед добавлением новых изображений

                // Добавляем дефолтное изображение снова
                $gallery.append('<img src="/images/default.png" id="default-upload" class="card-search-img" alt="Upload Image" style="cursor: pointer;">');

                // Добавляем остальные изображения
                data.forEach(function (imageName) {
                    var $img = $('<img>', {
                        src: '/images/' + imageName,
                        class: 'card-search-img',
                        alt: trimPunctuation(imageName).trim(),
                    });
                    $gallery.append($img);
                });

                // Плавное появление карточки
                $('.overlay-card-product').fadeIn(200);
            })
            .catch(error => console.error('Error fetching images:', error));
    });

});
//
// function uploadImage() {
//     var formData = new FormData();
//     var file = $("#image-upload")[0].files[0];
//
//     if (file) {
//         formData.append("image", file);
//
//         $.ajax({
//             url: '/api/images/upload',
//             type: 'POST',
//             data: formData,
//             processData: false,
//             contentType: false,
//             success: function(response) {
//                 // Помещаем имя файла в поле input с id="image"
//                 $('#image').val(file.name);
//
//                 // Очищаем input для загрузки файла
//                 $('#image-upload').val('');
//
//                 // Показываем сообщение об успешной загрузке
//                 $("#message").text("Файл '" + file.name + "' успешно загружен.");
//
//                 // Опционально: скрываем сообщение через некоторое время
//                 setTimeout(function() {
//                     $("#message").text("");
//                 }, 3000);  // скрываем сообщение через 3 секунды
//             },
//             error: function(response) {
//                 $("#message").text("Ошибка при загрузке изображения");
//             }
//         });
//     } else {
//         $("#message").text("Пожалуйста, выберите файл для загрузки");
//     }
// }