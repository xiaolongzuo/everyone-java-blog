## Cache模块接口说明

### 资源上传公共接口

返回用户上传成功的服务器资源路径及资源原名称。

- **URL**

  cache/upload

- **参数**

    - uid：用户编号不能为空；
    - file：资源文件名称；

- **HTTP Method**

  `POST`

- **成功示例**


  - **上传成功示例**

    ```json
    {
        "res": [
            {
                "originalFileName": "1428",
                "fileServerPath": "upload/33/1463482127960.PNG"
            },
            {
                "originalFileName": "1459407902107",
                "fileServerPath": "upload/33/1463482127964.PNG"
            }
        ]
    }
    ```

  ​

- **上传失败示例**

  ```json
    {
        "err": [
            {
                "errorCode": 1002,
                "errorMessage": "上传资源类型不正确"
            }
        ]
    }

   ```
    ​

- **errorCode说明**

  - 1000：用户编号不能为空；
  - 1001：上传资源不能为空；
  - 1002：上传资源类型不正确。
  - 1003：上传资源失败。

---
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

    `500`

  - **Content**

    `{"error":"异常发生了"}`

- **actionType类型说明**
  - 1：推荐；
  - 2：评论；
  - 3：阅读。