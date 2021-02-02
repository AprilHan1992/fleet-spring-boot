// 代码生成文件路径
namespace java com.fleet.thrift.common.gen

// 将 shrift 的数据类型格式转换为 java 习惯的格式
typedef i16 short
typedef i32 int
typedef i64 long
typedef string String
typedef bool boolean

// 定义用户对象
struct User {
    1:optional int id,
    2:optional String name,
    3:optional int age
}

// 定义异常数据
exception DataException {
    1:optional int code,
    2:optional String msg
}

//定义操作学生的服务
service UserService {

    int insert(1:required User user) throws (1:DataException dataException),

    User get(1:required int id)  throws (1:DataException dataException)
}

// 用于自动生成代码的命令
// thrift-0.12.0.exe --gen java  ./../thrift/user.thrift
