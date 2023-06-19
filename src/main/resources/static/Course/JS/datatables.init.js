



(function($) {
    "use strict"
    //example 1
    var table = $('#example').DataTable({
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.13.4/i18n/zh-HANT.json"
        },
        createdRow: function ( row, data, index ) {
           $(row).addClass('selected')
        } 
    });
      
    table.on('click', 'tbody tr', function() {
    var $row = table.row(this).nodes().to$();
    var hasClass = $row.hasClass('selected');
    if (hasClass) {
        $row.removeClass('selected')
    } else {
        $row.addClass('selected')
    }
    })
    
    table.rows().every(function() {
    this.nodes().to$().removeClass('selected')
    });



    //example 2
    var table2 = $('#example2').DataTable( {
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.13.4/i18n/zh-HANT.json"
        },
        createdRow: function ( row, data, index ) {
            $(row).addClass('selected')
        },

        "scrollY":        "42vh",
        "scrollCollapse": true,
        "paging":         false
    });

    table2.on('click', 'tbody tr', function() {
        var $row = table2.row(this).nodes().to$();
        var hasClass = $row.hasClass('selected');
        if (hasClass) {
            $row.removeClass('selected')
        } else {
            $row.addClass('selected')
        }
    })
        
    table2.rows().every(function() {
        this.nodes().to$().removeClass('selected')
    });
	
	// 
	var table = $('#example3, #example4, #example5, myTable').DataTable();
	$('#example tbody').on('click', 'tr', function () {
		var data = table.row( this ).data();
	});
    var table = new DataTable('#example3', {
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/zh-HANT.json',
        },
    });


})(jQuery);