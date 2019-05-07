var config = {
    el: "#app",
    data() {
        return {
            sort: {
                name: '',
                order: 'asc'
            },
            selectIndex: -1,
            columns: [
                { title: '姓名', name: 'name' },
                { title: '类别', name: 'calories', align: 'center', sortable: true },
                { title: 'Fat (g)', name: 'fat', align: 'center', sortable: true },
                { title: 'Carbs (g)', name: 'carbs', align: 'center', sortable: true },
                { title: 'Protein (g)', name: 'protein', align: 'center', sortable: true },
                { title: '钢铁含量', name: 'iron', align: 'center', sortable: true }
            ],

            list: [
                {
                    name: 'Frozen Yogurt',
                    calories: 1,
                    fat: 6.0,
                    carbs: 24,
                    protein: 4.0,
                    iron: 1
                },
                {
                    name: 'Ice cream sandwich',
                    calories: 2,
                    fat: 9.0,
                    carbs: 37,
                    protein: 4.3,
                    iron: 1
                },
                {
                    name: 'Eclair',
                    calories: 3,
                    fat: 16.0,
                    carbs: 23,
                    protein: 6.0,
                    iron: 7
                },
                {
                    name: 'Cupcake',
                    calories: 4,
                    fat: 3.7,
                    carbs: 67,
                    protein: 4.3,
                    iron: 8
                },
                {
                    name: 'Gingerbread',
                    calories: 1,
                    fat: 16.0,
                    carbs: 49,
                    protein: 3.9,
                    iron: 16
                },
                {
                    name: 'Jelly bean',
                    calories: 3,
                    fat: 0.0,
                    carbs: 94,
                    protein: 0.0,
                    iron: 0
                },
                {
                    name: 'Lollipop',
                    calories: 2,
                    fat: 0.2,
                    carbs: 98,
                    protein: 0,
                    iron: 2
                },
                {
                    name: 'Honeycomb',
                    calories: 4,
                    fat: 3.2,
                    carbs: 87,
                    protein: 6.5,
                    iron: 45
                },
                {
                    name: 'Donut',
                    calories: 1,
                    fat: 25.0,
                    carbs: 51,
                    protein: 4.9,
                    iron: 22
                },
                {
                    name: 'KitKat',
                    calories: 4,
                    fat: 26.0,
                    carbs: 65,
                    protein: 7,
                    iron: 6
                }
            ]
        };
    },
    methods: {
        handleSortChange({ name, order }) {
            this.list = this.list.sort((a, b) => order === 'asc' ? a[name] - b[name] : b[name] - a[name]);
        },
        caloriesFormatter: function (type) {
            var result = "";
            switch (type) {
                case 1: result = "新建"; break;
                case 2: result = "设计师完成"; break;
                case 3: result = "样师抢单"; break;
                case 4: result = "样师完成"; break;
                case 5: result = "版师抢单"; break;
                default: result = "未识别的状态"; break;
            }
            return result;
        }
    }
};
window.vm = new Vue(config);