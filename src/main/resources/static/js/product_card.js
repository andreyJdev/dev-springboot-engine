function trimPunctuation(str) {
    return str.replace(/^[\p{P}\p{S}]+|[\p{P}\p{S}]+$/gu, '');
}

$(document).ready(function () {
    // Обработчик клика по ссылке "Подробнее"
    $('.open-product-card').on('click', function (event) {
        event.preventDefault(); // Предотвращаем переход по ссылке
        var productId = $(this).closest('.open-product-card').find('.card-description').data('product-id'); // Получаем ID продукта

        // Функция для загрузки данных и отображения карточки
        function loadProduct(productId) {
            $.getJSON(`api/products/${productId}`, function (data) {
                var description = data.description.trim();
                var image = data.image.trim();
                var name = trimPunctuation(data.name).trim();
                var selection = trimPunctuation(data.selection).trim();
                var selectionMini = trimPunctuation(data.selectionMini).trim();
                var time = trimPunctuation(data.time).trim();
                var strength = trimPunctuation(data.strength).trim();
                var cluster = trimPunctuation(data.cluster).trim();
                var berry = trimPunctuation(data.berry).trim();
                var taste = trimPunctuation(data.taste).trim();
                var resistanceCold = trimPunctuation(data.resistanceCold).trim();
                var priceSeed = trimPunctuation(data.priceSeed).trim();
                var priceCut = trimPunctuation(data.priceCut).trim();

                var cardTemplate = `
                    <div class="overlay-card-product" style="display: none;">
                            <div class="card-product">
                                <span class="close-btn text-right">&times;</span>
                                <div class="container">
                                    <div class="row">
                                        <div class="col">
                                            <h2>${name}</h2>
                                            <div class="card-product-image">
                                                <img src="/images/${image}" alt="Картинка">
                                            </div>
                                        </div>
                                        <div class="col" style="align-items: flex-start !important;">
                                            <div class="card-product-content">
                                               ${description !== "" ?
                    '<div class="product-content-section"> <h2> Описание сорта </h2>' + '<p>' + description + '</p> </div>'
                    : ''}
                                                    <div class="product-content-section">  
                                                        <h2>Характеристики</h2>
                                                        <ul class="product-content-list">                 
                                                           ${selection !== "" ?
                    '<li class="product-content-item"><strong>Селекция: </strong>' + selection + '.</li>' : 
                    '<li class="product-content-item"><strong>Селекция: </strong>' + selectionMini + '.</li>'}
                                                            ${time !== "" ?
                    '<li class="product-content-item"><strong>Срок созревания: </strong>' + time + '.</li>' : ''}
                                                           ${strength !== "" ?
                    '<li class="product-content-item"><strong>Сила роста: </strong>' + strength + '.</li>' : ''}
                                                           ${cluster !== "" ?
                    '<li class="product-content-item"><strong>Гроздь: </strong>' + cluster + '.</li>' : ''}
                                                           ${berry !== "" ?
                    '<li class="product-content-item"><strong>Ягода: </strong>' + berry + '.</li>' : ''}
                                                           ${taste !== "" ?
                    '<li class="product-content-item"><strong>Вкус и консистенция мякоти: </strong>' + taste + '.</li>' : ''}
                                                           ${resistanceCold !== "" ?
                    '<li class="product-content-item"><strong>Морозо-стойкость: </strong>' + resistanceCold + '.</li>' : ''}
                                                    </div>
<div class="d-flex justify-content-end mt-4">
    <div class="price-section">
        ${priceSeed !== "" ?
                    '<p><span class="fw-600 fs-4 price-text">Цена саженца: </span><span class="display-6 fw-bold" id="price-seed">' + priceSeed + '&#8381;</span></p>' : ''}
        ${priceCut !== "" ?
                    '<p><span class="fw-600 fs-4">Цена черенка: </span><span class="display-6 fw-bold" id="price-cut">' + priceCut + '&#8381;</span></p>' : ''}
    </div>    
</div>
</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    </div>`;
                $('body').append(cardTemplate);
                $('.overlay-card-product').fadeIn(200);


                // Обработчик для закрытия карточки
                $('.close-btn').on('click', function () {
                    $(this).closest('.overlay-card-product').fadeOut(200, function () {
                        $(this).remove();
                    });
                });
                // Обработчик для закрытия карточки по клику на сам элемент
                $('.overlay-card-product').on('click', function (event) {
                    if (!$(event.target).closest('.card-product').length) {
                        $(this).fadeOut(200, function () {
                            $(this).remove();
                        });
                    }
                });
            });
        }

        // Вызов функции с конкретным productId
        loadProduct(productId);
    });
});