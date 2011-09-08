$(document).ready(function()
{
	//placeholder
	$('input,textarea').placeholder({'css':'placeholder2'});
	
	//wkslider
	$('.wkslider').wkslider({activate : 'o_active', selector : '', autoplay:true, time:5000});
	
	//datepicker
	$('input[type="date"]').datepicker({dateFormat: 'yy-mm-dd'});
	
	//add new user
	$('.add').bind('click',function()
			{
				createRow($(this).prev('table'));
			});
});

function createRow(table)
{
	var present = getUsers(table);
	var tr = $(document.createElement('tr'));
	var select = $(document.createElement('select'));
	var input = $(document.createElement('input')).prop({'type':'text','name':'amount'});
	var a = $(document.createElement('a')).prop({'text':'save'}).bind('click',function(){save($(this).closest('tr'));});
	
	tr.append($(document.createElement('td')));
	tr.append($(document.createElement('td')));
	tr.append($(document.createElement('td')));
	table.find('tbody').append(tr);
	
	var data = _.reject(users,function(user){return _.detect(present,function(p){return p == user.name;});});
	
	_.each(data,function(user)
			{
				var option = $(document.createElement('option'));
				option.attr('value',user.id);
				option.text(user.username);
				select.append(option);				
			});
	
	tr.find('td').eq(0).append(select);
	tr.find('td').eq(1).append(input);
	tr.find('td').eq(2).append(a);
	
	table.find('tbody').append(tr);
}

function getUsers(table)
{
	var users = [];
	table.find('tbody tr').each(function(i,el)
			{
				users.push($(el).eq(0).text());
			});
	return users;
}

function save(tr)
{
	var user = tr.find('td').eq(0).find('select').val();
	var expense = tr.closest('table').prop('id');
	var amount = tr.find('td').eq(1).find('input').val();
	
	$.ajax
	({
		url		: '/ce/s/expense?action=addshare',
		type	: 'post',
		data	: {'uid':user,'eid':expense,'amount':amount},
		success : function(){flatRow(tr);},
		error	: function(){alert('error while storing this share');}
	});
}

function flatRow(tr)
{
	var user = tr.find('td').eq(0).find('select option:selected').text();
	var amount = tr.find('td').eq(1).find('input').val();
	
	tr.find('td').eq(0).empty().text(user);
	tr.find('td').eq(1).empty().text(amount);
	tr.find('td').eq(2).empty();
}