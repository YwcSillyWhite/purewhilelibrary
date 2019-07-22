package com.purewhite.ywc.frame.wheel;

import java.util.List;

public class WheelBean {

    /**
     * status : 1
     * message : 成功
     * data : [{"id":1,"name":"土地","children":[{"id":1,"name":"农用地","children":[{"id":4,"name":"耕地","children":[{"id":5,"name":"水田"},{"id":6,"name":"水浇地"},{"id":7,"name":"旱地"},{"id":72,"name":"蔬菜大棚"},{"id":73,"name":"食用菌棚"},{"id":74,"name":"鱼塘"}]},{"id":8,"name":"林地","children":[{"id":9,"name":"乔木林地"},{"id":10,"name":"竹林地"},{"id":11,"name":"红树林地"},{"id":12,"name":"森林沼泽"},{"id":13,"name":"灌木林地"},{"id":14,"name":"灌丛沼泽"},{"id":15,"name":"其它林地"}]},{"id":16,"name":"园地","children":[{"id":17,"name":"果园"},{"id":18,"name":"茶园"},{"id":19,"name":"其它园地"}]},{"id":20,"name":"草地","children":[{"id":21,"name":"天然牧草地"},{"id":22,"name":"沼泽草地"},{"id":23,"name":"人工牧草地"}]},{"id":24,"name":"交通运输用地","children":[{"id":25,"name":"农村道路"}]},{"id":26,"name":"水域及水利设施用地","children":[{"id":27,"name":"水库水面"},{"id":28,"name":"坑塘水面"},{"id":29,"name":"沟渠"}]},{"id":30,"name":"其它土地","children":[{"id":31,"name":"设施农用地"},{"id":32,"name":"田坎"}]}]},{"id":2,"name":"村镇用地","children":[{"id":33,"name":"居住用地","children":[{"id":34,"name":"闲置宅基地"},{"id":35,"name":"新建农房"},{"id":36,"name":"老旧农房"},{"id":37,"name":"房屋"},{"id":38,"name":"租赁房用地"}]},{"id":39,"name":"乡企用地","children":[{"id":40,"name":"镇办企业"},{"id":41,"name":"村办企业"},{"id":42,"name":"联营企业"},{"id":43,"name":"合作企业"},{"id":44,"name":"个体企业"}]},{"id":45,"name":"工矿用地","children":[{"id":46,"name":"工业用地"},{"id":47,"name":"采矿用地"},{"id":48,"name":"仓储用地"},{"id":49,"name":"厂房"},{"id":50,"name":"仓库"}]},{"id":51,"name":"商业用地","children":[{"id":52,"name":"交易市场"},{"id":53,"name":"物流配送"},{"id":54,"name":"营业设施"},{"id":55,"name":"商场超市"},{"id":56,"name":"写字楼"}]},{"id":57,"name":"综合用地","children":[]},{"id":58,"name":"其它非农地","children":[]}]},{"id":3,"name":"未利用地","children":[{"id":59,"name":"四荒地","children":[{"id":68,"name":"荒山"},{"id":69,"name":"荒坡"},{"id":70,"name":"荒丘"},{"id":71,"name":"荒滩"}]},{"id":60,"name":"河流水面","children":[]},{"id":61,"name":"湖泊水面","children":[]},{"id":62,"name":"内陆滩涂","children":[]},{"id":63,"name":"沼泽地","children":[]},{"id":64,"name":"盐碱地","children":[]},{"id":65,"name":"沙地","children":[]},{"id":66,"name":"裸土地","children":[]},{"id":67,"name":"裸岩石砾地","children":[]}]}]},{"id":2,"name":"房产","children":[{"id":12,"name":"厂房","children":[]},{"id":13,"name":"仓库","children":[]},{"id":14,"name":"商铺","children":[]},{"id":15,"name":"办公楼","children":[]},{"id":16,"name":"其他","children":[]}]},{"id":3,"name":"生产设施设备","children":[{"id":17,"name":"收割机","children":[]},{"id":18,"name":"播种机","children":[]},{"id":19,"name":"拖拉机","children":[]},{"id":20,"name":"挖掘机","children":[]},{"id":21,"name":"智能撒播无人机","children":[]},{"id":22,"name":"其他","children":[]}]},{"id":4,"name":"工具(交通工具等)","children":[{"id":23,"name":"汽车","children":[]},{"id":24,"name":"其他","children":[]}]},{"id":5,"name":"其他资产","children":[]}]
     */

    private int status;
    private String message;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 土地
         * children : [{"id":1,"name":"农用地","children":[{"id":4,"name":"耕地","children":[{"id":5,"name":"水田"},{"id":6,"name":"水浇地"},{"id":7,"name":"旱地"},{"id":72,"name":"蔬菜大棚"},{"id":73,"name":"食用菌棚"},{"id":74,"name":"鱼塘"}]},{"id":8,"name":"林地","children":[{"id":9,"name":"乔木林地"},{"id":10,"name":"竹林地"},{"id":11,"name":"红树林地"},{"id":12,"name":"森林沼泽"},{"id":13,"name":"灌木林地"},{"id":14,"name":"灌丛沼泽"},{"id":15,"name":"其它林地"}]},{"id":16,"name":"园地","children":[{"id":17,"name":"果园"},{"id":18,"name":"茶园"},{"id":19,"name":"其它园地"}]},{"id":20,"name":"草地","children":[{"id":21,"name":"天然牧草地"},{"id":22,"name":"沼泽草地"},{"id":23,"name":"人工牧草地"}]},{"id":24,"name":"交通运输用地","children":[{"id":25,"name":"农村道路"}]},{"id":26,"name":"水域及水利设施用地","children":[{"id":27,"name":"水库水面"},{"id":28,"name":"坑塘水面"},{"id":29,"name":"沟渠"}]},{"id":30,"name":"其它土地","children":[{"id":31,"name":"设施农用地"},{"id":32,"name":"田坎"}]}]},{"id":2,"name":"村镇用地","children":[{"id":33,"name":"居住用地","children":[{"id":34,"name":"闲置宅基地"},{"id":35,"name":"新建农房"},{"id":36,"name":"老旧农房"},{"id":37,"name":"房屋"},{"id":38,"name":"租赁房用地"}]},{"id":39,"name":"乡企用地","children":[{"id":40,"name":"镇办企业"},{"id":41,"name":"村办企业"},{"id":42,"name":"联营企业"},{"id":43,"name":"合作企业"},{"id":44,"name":"个体企业"}]},{"id":45,"name":"工矿用地","children":[{"id":46,"name":"工业用地"},{"id":47,"name":"采矿用地"},{"id":48,"name":"仓储用地"},{"id":49,"name":"厂房"},{"id":50,"name":"仓库"}]},{"id":51,"name":"商业用地","children":[{"id":52,"name":"交易市场"},{"id":53,"name":"物流配送"},{"id":54,"name":"营业设施"},{"id":55,"name":"商场超市"},{"id":56,"name":"写字楼"}]},{"id":57,"name":"综合用地","children":[]},{"id":58,"name":"其它非农地","children":[]}]},{"id":3,"name":"未利用地","children":[{"id":59,"name":"四荒地","children":[{"id":68,"name":"荒山"},{"id":69,"name":"荒坡"},{"id":70,"name":"荒丘"},{"id":71,"name":"荒滩"}]},{"id":60,"name":"河流水面","children":[]},{"id":61,"name":"湖泊水面","children":[]},{"id":62,"name":"内陆滩涂","children":[]},{"id":63,"name":"沼泽地","children":[]},{"id":64,"name":"盐碱地","children":[]},{"id":65,"name":"沙地","children":[]},{"id":66,"name":"裸土地","children":[]},{"id":67,"name":"裸岩石砾地","children":[]}]}]
         */

        private int id;
        private String name;
        private List<ChildrenBeanXX> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBeanXX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanXX> children) {
            this.children = children;
        }

        public static class ChildrenBeanXX {
            /**
             * id : 1
             * name : 农用地
             * children : [{"id":4,"name":"耕地","children":[{"id":5,"name":"水田"},{"id":6,"name":"水浇地"},{"id":7,"name":"旱地"},{"id":72,"name":"蔬菜大棚"},{"id":73,"name":"食用菌棚"},{"id":74,"name":"鱼塘"}]},{"id":8,"name":"林地","children":[{"id":9,"name":"乔木林地"},{"id":10,"name":"竹林地"},{"id":11,"name":"红树林地"},{"id":12,"name":"森林沼泽"},{"id":13,"name":"灌木林地"},{"id":14,"name":"灌丛沼泽"},{"id":15,"name":"其它林地"}]},{"id":16,"name":"园地","children":[{"id":17,"name":"果园"},{"id":18,"name":"茶园"},{"id":19,"name":"其它园地"}]},{"id":20,"name":"草地","children":[{"id":21,"name":"天然牧草地"},{"id":22,"name":"沼泽草地"},{"id":23,"name":"人工牧草地"}]},{"id":24,"name":"交通运输用地","children":[{"id":25,"name":"农村道路"}]},{"id":26,"name":"水域及水利设施用地","children":[{"id":27,"name":"水库水面"},{"id":28,"name":"坑塘水面"},{"id":29,"name":"沟渠"}]},{"id":30,"name":"其它土地","children":[{"id":31,"name":"设施农用地"},{"id":32,"name":"田坎"}]}]
             */

            private int id;
            private String name;
            private List<ChildrenBeanX> children;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ChildrenBeanX> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBeanX> children) {
                this.children = children;
            }

            public static class ChildrenBeanX {
                /**
                 * id : 4
                 * name : 耕地
                 * children : [{"id":5,"name":"水田"},{"id":6,"name":"水浇地"},{"id":7,"name":"旱地"},{"id":72,"name":"蔬菜大棚"},{"id":73,"name":"食用菌棚"},{"id":74,"name":"鱼塘"}]
                 */

                private int id;
                private String name;
                private List<ChildrenBean> children;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<ChildrenBean> getChildren() {
                    return children;
                }

                public void setChildren(List<ChildrenBean> children) {
                    this.children = children;
                }

                public static class ChildrenBean {
                    /**
                     * id : 5
                     * name : 水田
                     */

                    private int id;
                    private String name;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }
    }
}
