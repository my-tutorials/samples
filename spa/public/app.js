/* Module */
var App = {
	Models : {},
	Collections : {},
	Views : {},
	start : function() { start(); }

}

App.Models.Human = Backbone.Model.extend({
	urlRoot  : "/human"
});

App.Collections.Humans = Backbone.Collection.extend({
	url : "/humans",
	model : App.Models.Human
	
});

App.Views.HumansListView = Backbone.View.extend({
    el : $("#humans_list"),
    initialize : function () {
        this.template = $("#humans_list_template").html();

        //dès que la collection "change" j'actualise le rendu de la vue
        _.bindAll(this, 'render');
        this.collection.bind('reset', this.render);
        this.collection.bind('change', this.render);
        this.collection.bind('add', this.render);
        this.collection.bind('remove', this.render);

    },
    render : function () {
        var renderedContent = Mustache.to_html(this.template, {humans : this.collection.toJSON()} );
        this.$el.html(renderedContent);
    }
});


App.Views.HumansListAgainView = Backbone.View.extend({
	el : $("#humans_list_again"),
	initialize : function (blog) {
		this.template = _.template($("#humans_list_again_template").html());

        _.bindAll(this, 'render');
        this.collection.bind('reset', this.render);
        this.collection.bind('change', this.render);
        this.collection.bind('add', this.render);
        this.collection.bind('remove', this.render);
	},
	render : function () {
        var renderedContent = this.template({humans : this.collection.models });
        this.$el.html(renderedContent);			
	}			
});



function start() {

	console.log("Démarrage de l'application Backbone");

	window.humansCollection = new App.Collections.Humans();

	window.humansListView = new App.Views.HumansListView({collection : humansCollection});

	window.humansListAgainView = new App.Views.HumansListAgainView({collection : humansCollection});

	humansCollection.fetch({
		success : function(data) { console.log(data); },
		error : function(err) { throw err; }
	});
}