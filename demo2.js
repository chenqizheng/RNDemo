import React from 'react';
import {AppRegistry, StyleSheet, Text, View, Image} from 'react-native';

class HelloWorld extends React.Component {
    render() {
        return (
            <View>
                <Image style={styles.icon} source={require('./icon.png')}/>
                <Image
                    style={styles.logo}
                    source={{
                    uri: 'http://172.29.182.3:8887/icon.png'
                }}/>
            </View>
        )
    }
}
var styles = StyleSheet.create({
    icon: {
        height: 40,
        width: 50
    },
    logo: {
        height: 40,
        width: 50
    }
});

AppRegistry.registerComponent('demo2', () => HelloWorld);
