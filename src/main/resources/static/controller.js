$(document).ready(function () {

    // Get all the stores
    $('#get_all').click(function (){
        $.get("/shops/",
        function(stores) {
            $('#result').empty();
            $.each(stores, function(i, store) {
                $('#result').append("Store name: " + store.name + ", Capacity: " + store.capacity + " painting(s).<br>");
            });
        });
    });

    // Get all the paintings of a given store
    $('#get_one').click(function (){
        $('#result_get_one').empty();
        if ($('#id_one').val()) {
            var url="/shops/" + $('#id_one').val() + "/pictures";
            $.get(url,
            function(paintings) {
                if (jQuery.isEmptyObject(paintings)) {
                    $('#result_get_one').append("Paintings? Which paintings? This is a respectable collars shop.<br>");
                } else {
                $.each(paintings, function(i, painting) {
                    $('#result_get_one').append("Painting name: " + painting.name + ", Painter: " + painting.painter + ", Price: " + painting.price + "€, Entry date: " + painting.entryDate + "<br>");
                    });
                }
            }).fail(function(stat){
                alert(stat.responseText);
            });
        } else {
            alert("You must introduce a store id.");
        }
    });

    // Fire all of the paintings of a given store
    $('#fire').click(function (){
        $('#result_get_one').empty();
        if ($('#id_one').val()) {
            $.ajax({
                url: "/shops/" + $('#id_one').val() + "/pictures",
                type: "DELETE",
                success: function(){
                    $('#result_get_one').append("Paintings? Which paintings? This is a respectable collars shop.<br>");
                },
                error: function(stat) {
                    alert(stat.responseText);
                }
            });
        } else {
            alert("You must introduce a store id.");
        }
    });

    // Add a new store to the system
    $('#add_store').click(function (){
        if ($('#store_name').val() && $('#store_capacity').val()){
            $.ajax({
                url: "/shops/",
                dataType: "json",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({"name": $('#store_name').val(), "capacity": $('#store_capacity').val()}),
                processData: false,
                success: function (store) {
                    $('#result_add_store').empty();
                    $('#result_add_store').append("Successfully added store!!!<br>Store name: " + store.name + "<br>Capacity: " + store.capacity);
                },
                error: function(stat) {
                    alert(stat.responseText);
                }
            });
        } else {
            alert("Please fill in name and capacity to create a new store.");
        }
    });

    // Add a new painting to a given store
    $('#add_painting').click (function(){
        if (!$('#store_id').val() || !$('#painting_name').val()) {
            alert("You must introduce at least store id and painting name.");
        } else {
            $(result_add_painting).empty();
            $.ajax({
                url: "/shops/" + $(store_id).val() + "/pictures",
                dataType: "json",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({"name": $(painting_name).val(), "painter": $(painter).val()}),
                processData: false,
                success: function() {
                    $(result_add_painting).append("Painting \"" + $(painting_name).val() + "\" successfully added!!!");
                },
                error: function(stat) {
                    alert(stat.responseText);
                }
            });
        }
    });
});