// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.fleet.grpc.common;

public final class UserProto {
  private UserProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BooleanResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BooleanResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\"4\n\013UserRequest\022\n\n\002id\030\001 \001(\005\022" +
      "\014\n\004name\030\002 \001(\t\022\013\n\003age\030\003 \001(\005\"5\n\014UserRespon" +
      "se\022\n\n\002id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022\013\n\003age\030\003 \001(" +
      "\005\"\037\n\017BooleanResponse\022\014\n\004bool\030\001 \001(\0102_\n\013Us" +
      "erService\022$\n\003get\022\014.UserRequest\032\r.UserRes" +
      "ponse\"\000\022*\n\006insert\022\014.UserRequest\032\020.Boolea" +
      "nResponse\"\000B$\n\025com.fleet.grpc.commonB\tUs" +
      "erProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_UserRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_UserRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserRequest_descriptor,
        new java.lang.String[] { "Id", "Name", "Age", });
    internal_static_UserResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_UserResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "Age", });
    internal_static_BooleanResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_BooleanResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BooleanResponse_descriptor,
        new java.lang.String[] { "Bool", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
