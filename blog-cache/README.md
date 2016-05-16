## Cache模块接口说明

### 文章排名查询接口

返回每种类别排名前位的文章信息。

- **URL**

  cache/article/rank

- **HTTP Method**

  `GET`

- **成功返回示例**

  - **HTTP Status Code**

    `200`

  - **返回信息**

    ```json
    [
        {
            "actionType": "1",
            "dataResult": [
                {
                    "categoryInfo": {
                        "id": "1",
                        "categoryName": "技术"
                    },
                    "articleInfo": {
                        "id": "",
                        "createTime": "",
                        "webUserId": "",
                        "title": "",
                        "thumbupTimes": "200"
                    }
                },
                {
                    "categoryInfo": {
                        "categoryId": "2",
                        "categoryName": "生活"
                    },
                    "articleInfo": {
                        "id": "",
                        "createTime": "",
                        "webUserId": "",
                        "title": "",
                        "thumbupTimes": "100"
                    }
                }
            ]
        },
        {
            "actionType": "2",
            "dataResult": [
                {
                    "categoryInfo": {
                        "id": "1",
                        "categoryName": "技术"
                    },
                    "articleInfo": {
                        "id": "",
                        "createTime": "",
                        "webUserId": "",
                        "title": "",
                        "commentTimes": "400"
                    }
                },
                {
                    "categoryInfo": {
                        "categoryId": "2",
                        "categoryName": "生活"
                    },
                    "articleInfo": {
                        "id": "",
                        "createTime": "",
                        "webUserId": "",
                        "title": "",
                        "commentTimes": "200"
                    }
                }
            ]
        },
        {
            "actionType": "3",
            "dataResult": [
                {
                    "categoryInfo": {
                        "id": "1",
                        "categoryName": "技术"
                    },
                    "articleInfo": {
                        "id": "",
                        "createTime": "",
                        "webUserId": "",
                        "title": "",
                        "readTimes": "100"
                    }
                },
                {
                    "categoryInfo": {
                        "categoryId": "2",
                        "categoryName": "生活"
                    },
                    "articleInfo": {
                        "id": "",
                        "createTime": "",
                        "webUserId": "",
                        "title": "",
                        "readTimes": "200"
                    }
                }
            ]
        }
    ]
    ```

  ​

- **异常返回示例**

  - **HTTP Status Code**

    `401`

  - **Content**

    `{"error":"异常发生了"}`

    ​

- **actionType类型说明**

  - 1：推荐；
  - 2：评论；
  - 3：阅读。