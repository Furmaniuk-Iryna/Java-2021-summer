package com.example.final_project.model.entity;

public class Address {
        private long id;
        private String address_en;
        private String address_uk;
        private Direction direction;

        public Address() {
        }

        public Address(long id, String address_en, String address_uk, Direction direction) {
                this.id = id;
                this.address_en = address_en;
                this.address_uk = address_uk;
                this.direction = direction;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getAddress_en() {
                return address_en;
        }

        public void setAddress_en(String address_en) {
                this.address_en = address_en;
        }

        public String getAddress_uk() {
                return address_uk;
        }

        public void setAddress_uk(String address_uk) {
                this.address_uk = address_uk;
        }

        public Direction getDirection() {
                return direction;
        }

        public void setDirection(Direction direction) {
                this.direction = direction;
        }

        @Override
        public String toString() {
                return "Address{" +
                        "id=" + id +
                        ", address_en='" + address_en + '\'' +
                        ", address_uk='" + address_uk + '\'' +
                        ", direction=" + direction +
                        '}';
        }
}
