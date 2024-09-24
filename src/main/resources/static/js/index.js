$(document).ready(function () {
    // Обработчик клика по ссылке "Подробнее"
    $('.card-description').on('click', function (event) {
        event.preventDefault(); // Предотвращаем переход по ссылке
        var productId = $(this).data('product-id'); // Получаем ID продукта

        // Функция для загрузки данных и отображения карточки
        function loadProduct(productId) {
            $.getJSON(`api/products/${productId}`, function (data) {
                var cardTemplate = `
                    <div class="overlay-card-product">
                            <div class="card-product">
                                <span class="close-btn text-right">&times;</span>
                                <div class="container">
                                    <div class="row">
                                        <div class="col">
                                            <h2>${data.name}</h2>
                                            <div class="card-product-image">
                                                <img src="/images/${data.image}" alt="Картинка">
                                            </div>
                                        </div>
                                        <div class="col" style="align-items: flex-start !important;">
                                            <div class="card-product-content">
                                               ${data.description !== "-" && data.description !== "" ?
                    '<div class="product-content-section"> <h2> Описание сорта </h2>' + '<p>' + data.description + '</p> </div>'
                    : ''}
                                                    <div class="product-content-section">  
                                                        <h2>Характеристики</h2>
                                                        <ul class="product-content-list">                 
                                                           ${data.time.trim() !== "" && data.time.trim() !== "-" ?
                    '<li class="product-content-item"><strong>Срок созревания: </strong>' + data.time.trim() + '.</li>' : ''}
                                                           ${data.strength.trim() !== "" && data.strength.trim() !== "-" ?
                    '<li class="product-content-item"><strong>Сила роста: </strong>' + data.strength.trim() + '.</li>' : ''}
                                                           ${data.cluster.trim() !== "" && data.cluster.trim() !== "-" ?
                    '<li class="product-content-item"><strong>Гроздь: </strong>' + data.cluster.trim() + '.</li>' : ''}
                                                           ${data.berry.trim() !== "" && data.berry.trim() !== "-" ?
                    '<li class="product-content-item"><strong>Ягода: </strong>' + data.berry.trim() + '.</li>' : ''}
                                                           ${data.taste.trim() !== "" && data.taste.trim() !== "-" ?
                    '<li class="product-content-item"><strong>Вкус и консистенция мякоти: </strong>' + data.taste.trim() + '.</li>' : ''}
                                                           ${data.resistanceCold.trim() !== "" && data.resistanceCold.trim() !== "-" ?
                    '<li class="product-content-item"><strong>Морозо-стойкость: </strong>' + data.resistanceCold.trim() + '.</li>' : ''}
                                                    </div>
<div class="d-flex justify-content-end mt-4">
    <div class="price-section">
        ${data.priceSeed.trim() !== "" && data.priceSeed.trim() !== "-" ?
                    '<p><span class="fw-600 fs-4 price-text">Цена саженца: </span><span class="display-6 fw-bold" id="price-seed">' + data.priceSeed.trim() +'&#8381;</span></p>' : ''}
        ${data.priceCut.trim() !== "" && data.priceCut.trim() !== "-" ?
                    '<p><span class="fw-600 fs-4">Цена черенка: </span><span class="display-6 fw-bold" id="price-cut">' + data.priceCut.trim() + '&#8381;</span></p>' : ''}
    </div>    
</div>
</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    </div>`;
                $('body').append(cardTemplate);

                // Обработчик для закрытия карточки
                $('.close-btn').on('click', function () {
                    $(this).closest('.overlay-card-product').remove();
                });
            });
        }

        // Вызов функции с конкретным productId
        loadProduct(productId);
    });
});