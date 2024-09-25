$(document).ready(function() {
    $('.delete-form').on('submit', function(e) {
        e.preventDefault(); // Предотвращаем стандартное поведение формы

        var form = $(this);
        var productId = form.find('input[type="submit"]').data('product-id');
        var actionUrl = form.attr('action');

        if (confirm('Вы уверены, что хотите удалить этот элемент?')) {
            $.ajax({
                url: actionUrl,
                type: 'POST',
                success: function(response) {
                    form.closest('tr').remove(); // Удаляем строку таблицы
                    alert('Элемент успешно удален.');
                },
                error: function(xhr, status, error) {
                    alert('Произошла ошибка при удалении элемента.');
                }
            });
        }
    });
});