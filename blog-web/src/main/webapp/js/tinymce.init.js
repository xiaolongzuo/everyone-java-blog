function tinymceInit(settings) {
	$(document).ready(function() {
		var defaultSettings = {width:600,height:400,content:'',skin:'lightgray'};
		$.extend(defaultSettings,settings);
	    tinymce.init({
	        selector: "textarea.html_editor",
	        language: "zh_CN",
	        menubar : false,
	        skin: defaultSettings.skin,
	        width: defaultSettings.width,
	        height: defaultSettings.height,
            content_css: contextPath + '/css/content.css',
	        toolbar_items_size:'small',
	        setup: function(editor) {
	            editor.addButton('upload',
	            {
	                icon: 'print',
					title: '上传本地图片',
	                onclick: function() {
	                    editor.windowManager.open({
	                        title: "上传本地图片",
	                        url: contextPath + "/jsp/article/upload-image.jsp",
	                        width: 400,
	                        height: 150
	                    });
	                }
	            });
	            editor.addButton('insertcode',
				{
					icon: 'paste',
					title: '插入代码',
					onclick: function() {
						editor.windowManager.open({
							title: "插入代码",
							url: contextPath + "/jsp/article/insert-code.jsp",
							width: 800,
							height: 400
						});
					}
				});
                editor.addButton('settings',
                {
                    text: '文章设置',
                    title: '文章设置',
                    onclick: function() {
                        editor.windowManager.open({
                            title: "文章设置",
                            url: contextPath + "/jsp/article/article-settings.jsp",
                            width: 800,
                            height: 200
                        });
                    }
                });
                editor.addButton('save',
                {
                    text: '保存文章',
                    title: '保存文章',
                    onclick: function() {
                        $.ajax({
                            url: contextPath + "/Article/Add/UserArticle",
                            type: "post",
                            data: {},
                            dataType: "json",
                            success: function(data) {
                                if (!data || !data.userArticle) {
                                    alert("获取文章发生错误!");
                                } else {
                                    $("#title").val(data.userArticle.title);
                                    if (isInit) {
                                        tinymceSettings.content = data.userArticle.content;
                                        tinymceInit(tinymceSettings);
                                    } else {
                                        tinymce.activeEditor.setContent(data.userArticle.content);
                                    }
                                }
                            }
                        });
                    }
                });
                editor.addButton('publish',
                {
                    title: '发布文章',
                    text: '发布文章',
                    onclick: function() {
                        alert('发布');
                    }
                });
				editor.on('init', function(e) {
		            //editor.dom.addStyle('pre {border:1px solid #aaa;padding:5px;line-height:15px;background-color:#FFD700;} ');
		            //editor.dom.addStyle('blockquote {border: 1px solid #aaa;padding: 0px;} ');
		            if (defaultSettings.content) {
		            	editor.setContent(defaultSettings.content);
		            }
		        });
	        },
	        plugins: [
	            "advlist autolink lists link image charmap print preview anchor textcolor",
	            "searchreplace visualblocks code fullscreen",
	            "insertdatetime media table contextmenu paste emoticons"
	        ],
	        toolbar: ["undo redo | styleselect bold italic forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | code preview fullscreen ",
            "link upload image insertcode table blockquote media emoticons | settings | save | publish"]
	    });
	});
}