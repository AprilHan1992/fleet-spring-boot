// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.fleet.grpc.common;

public interface UserRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UserRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 age = 3;</code>
   * @return The age.
   */
  int getAge();
}
