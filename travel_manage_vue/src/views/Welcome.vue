<template>
	<div>
		<div  :style="backgroundDiv" style="min-height: 80vh">
		</div>
	</div>
</template>

<script>
    export default {
        name: "Welcome",
        data() {
            return {
                backgroundDiv: {
                    backgroundImage: 'url(' + require('../assets/bg1.jpeg') + ')',
                    backgroundRepeat: 'no-repeat',
                    backgroundSize: '100% 100%',
                }
            }
        }
    }
</script>

<style scoped>

</style>
