import React from 'react';
import { View, StyleSheet, Platform } from 'react-native';

const isWeb = Platform.OS === 'web';

export default function StadiumBackground() {
  if (!isWeb) return null;

  return (
    <View style={styles.container} pointerEvents="none">
      <View style={[styles.circle, styles.circle1]} />
      <View style={[styles.circle, styles.circle2]} />
      <View style={[styles.circle, styles.circle3]} />
      <View style={[styles.circle, styles.circle4]} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    overflow: 'hidden',
  },
  circle: {
    position: 'absolute',
    borderRadius: 9999,
    opacity: 0.05,
  },
  circle1: {
    width: 300,
    height: 300,
    top: -100,
    right: -50,
    backgroundColor: '#FFD700',
  },
  circle2: {
    width: 250,
    height: 250,
    bottom: -50,
    left: -100,
    backgroundColor: '#FFD700',
  },
  circle3: {
    width: 200,
    height: 200,
    top: '50%',
    right: '10%',
    backgroundColor: '#FFD700',
  },
  circle4: {
    width: 150,
    height: 150,
    bottom: '20%',
    left: '5%',
    backgroundColor: '#FFD700',
  },
});
