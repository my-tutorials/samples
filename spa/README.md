#Single Page Application

Sample for : [http://k33g.github.com/2012/10/05/NOMORESCALA.html](http://k33g.github.com/2012/10/05/NOMORESCALA.html)

***"Comment se passer des templates Scala dans Play!> 2"***


##Bootstrap


new App.Models.Address({
	town : "Paris", 
	land : "France"
}).save()




z = new App.Models.Human({firstName:"k33g",lastName:"org", age:43, address : new App.Models.Address({id:1, town : "Lyon", land : "France"}) })

z.save({},{
	success:function(data){ console.log("success : ", data); }, 
	error:function(err) { console.log("error : ", err); }  
})



z = new App.Models.Human({
	firstName:"Bill",
	lastName:"Murray", 
	age:60, 
	address : new App.Models.Address({
		id : 41,
		town : "Paris", 
		land : "France"
	}) 
}).save({},{
	success:function(data){ console.log("success : ", data); }, 
	error:function(err) { console.log("error : ", err); }  
})





